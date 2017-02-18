package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	String autoSelected;
	SendableChooser<String> chooser;
	AutoFunctions functions;
	AutoModes auto;
	AutoModesRightEncoder autoR;
	VisionTargeting vision;
	DriverStation station;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	DriverVision gearCamera;
	DriverVision highGoalCamera;
	GearMechanism gear;
	DriverControls driver;
	OperatorControls operator;
	FuelTank tank;
	double rotateToAngleRate;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Initilization
		station = DriverStation.getInstance();
		drive = new DriveTrain(vision);
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel);
		driver = new DriverControls(drive);
		operator = new OperatorControls(tank, gear, flywheel, vision, collector);


//		try{
//			gearCamera = new DriverVision("Gear Camera", 0);
//			gearCamera.startUSBCamera();			
//		}catch(Exception e){
//			System.out.println("Gear Camera Error: " + e.toString());
//		}
//		try{
//			highGoalCamera = new DriverVision("High Goal Camera", 1);
//			highGoalCamera.startUSBCamera();			
//		}catch(Exception e){
//			System.out.println("Gear Camera Error: " + e.toString());
//		}

		chooser = new SendableChooser<String>();
		
		//Basic AutoModes
		chooser.addDefault("Do Nothing", "DoNothing");
		chooser.addObject("Cross Baseline", "CrossBaseline");
		
		chooser.addObject("Auto One", "Auto1");
		
		//Starting at the Boiler AutoMode
		chooser.addObject("Start at Boiler", "StartBoiler");
		
		//Just the Gear AutoModes
		chooser.addObject("Middle Gear", "MiddleGear");
		chooser.addObject("Right Gear", "RightGear");
		chooser.addObject("Left Gear", "LeftGear");
		
		//Boiler to Gear AutoMode
		chooser.addObject("Boiler to Closest Gear", "BoilerSideGear");
		
		//Gear to Boiler AutoModes
		chooser.addObject("Red: Middle Gear to Boiler", "RedMiddleBoiler");
		chooser.addObject("Red: Right Gear to Boiler", "RedRightBoiler");
		chooser.addObject("Red: Left Gear to Boiler", "RedLeftBoiler");
		chooser.addObject("Blue: Middle Gear to Boiler", "BlueMiddleBoiler");
		chooser.addObject("Blue: Right Gear to Boiler", "BlueRightBoiler");
		chooser.addObject("Blue: Left Gear to Boiler", "BlueLeftBoiler");
		
		//tank to Boiler AutoModes
		chooser.addObject("Red: tank to Boiler", "RedtankBoiler");
		chooser.addObject("Blue: tank to Boiler", "BluetankBoiler");

		//Gear to tank AutoModes
		chooser.addObject("Red: Middle Gear to tank", "RedMiddletank");
		chooser.addObject("Red: Right Gear to tank", "RedRighttank");
		chooser.addObject("Red: Left Gear to tank", "RedLefttank");
		chooser.addObject("Blue: Middle Gear to tank", "BlueMiddletank");
		chooser.addObject("Blue: Right Gear to tank", "BlueRighttank");
		chooser.addObject("Blue: Left Gear to tank", "BlueLefttank");

		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", "Do Nothing");
		vision = new VisionTargeting("10.0.20.9");
		functions = new AutoFunctions(drive, flywheel, collector, vision, tank, gear);
		auto = new AutoModes(functions);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		auto.rightPeg();
		System.out.println("Encoder Value" + drive.masterLeft.getEncPosition()/1024*Math.PI*4);
		Scheduler.getInstance().run();
		switch (autoSelected) {
		case "Auto1":
			System.out.println("Running Auto One");
			auto.auto1();
			break;
			
		//Basic AutoModes
		case "DoNothing":
//			System.out.println("Do Nothing");
//			auto.doNothing();
			break;	
		case "CrossBaseline":
			if(drive.leftEncoder()){
				auto.crossBaseline();
			}else if(drive.rightEncoder()){
				autoR.crossBaseline();
			}else{
				auto.doNothing();
			}
			break;

		//Start at the Boiler AutoMode
		case "StartBoiler":
			System.out.println("Start at Boiler");
			if(drive.leftEncoder()){	
				auto.startBoiler();
			}else if(drive.rightEncoder()){
				autoR.startBoiler();
			}else{
				auto.doNothing();
			}
			break;
			
		//Just the Gear AutoModes
		case "MiddleGear":
			if(drive.leftEncoder()){
				auto.middlePeg();
			}else if(drive.rightEncoder()){
				autoR.middlePeg();
			}else{
				auto.doNothing();
			}
			break;		
 		case "RightGear":
			if(drive.leftEncoder()){
 				auto.rightPeg();
			}else if(drive.rightEncoder()){
				autoR.rightPeg();
			}else{
				auto.doNothing();
			}
			break;
		case "LeftGear":
			if(drive.leftEncoder()){	
				auto.leftPeg();
			}else if(drive.rightEncoder()){
				autoR.leftPeg();
			}else{
				auto.doNothing();
			}
			break;

		//AutoMode That Goes From the Boiler to a Gear
		case "BoilerSideGear":
			if(drive.leftEncoder()){	
				auto.boilerToSidePeg();
			}else if(drive.rightEncoder()){
				autoR.boilerToSidePeg();
			}else{
				auto.doNothing();
			}
			break;

		//AutoModes That Go From a Gear to the Boiler
		case "RedMiddleBoiler":
			if(drive.leftEncoder()){	
				auto.middleBoilerRed();
			}else if(drive.rightEncoder()){
				autoR.middleBoilerRed();
			}else{
				auto.doNothing();
			}
			break;			
		case "RedRightBoiler":
			if(drive.leftEncoder()){	
				auto.rightBoilerRed();
			}else if(drive.rightEncoder()){
				autoR.rightBoilerRed();
			}else{
				auto.doNothing();
			}
			break;
		case "RedLeftBoiler":
			if(drive.leftEncoder()){	
				auto.leftBoilerRed();
			}else if(drive.rightEncoder()){
				autoR.leftBoilerRed();
			}else{
				auto.doNothing();
			}
			break;
		case "BlueMiddleBoiler":
			if(drive.leftEncoder()){	
				auto.middleBoilerBlue();
			}else if(drive.rightEncoder()){
				autoR.middleBoilerBlue();
			}else{
				auto.doNothing();
			}
			break;			
		case "BlueRightBoiler":
			if(drive.leftEncoder()){	
				auto.rightBoilerBlue();
			}else if(drive.rightEncoder()){
				autoR.rightBoilerBlue();
			}else{
				auto.doNothing();
			}
			break;
		case "BlueLeftBoiler":
			if(drive.leftEncoder()){	
				auto.leftBoilerBlue();
			}else if(drive.rightEncoder()){
				autoR.leftBoilerBlue();
			}else{
				auto.doNothing();
			}
			break;
			
		//AutoModes That Go From the tank to the Boiler
		case "RedtankBoiler":
			if(drive.leftEncoder()){	
				auto.tankBoilerRed();
			}else if(drive.rightEncoder()){
				autoR.tankBoilerRed();
			}else{
				auto.doNothing();
			}
			break;			
		case "BluetankBoiler":
			if(drive.leftEncoder()){	
				auto.tankBoilerBlue();
			}else if(drive.rightEncoder()){
				autoR.tankBoilerBlue();
			}else{
				auto.doNothing();
			}
			break;
			
		//AutoModes That Place a Gear Then Go to the tank
		case "RedMiddletank":
			if(drive.leftEncoder()){	
				auto.middletankRed();
			}else if(drive.rightEncoder()){
				autoR.middletankRed();
			}else{
				auto.doNothing();
			}
			break;
		case "RedRighttank":
			if(drive.leftEncoder()){	
				auto.righttankRed();
			}else if(drive.rightEncoder()){
				autoR.righttankRed();
			}else{
				auto.doNothing();
			}
			break;
		case "RedLefttank":
			if(drive.leftEncoder()){	
				auto.lefttankRed();
			}else if(drive.rightEncoder()){
				autoR.lefttankRed();
			}else{
				auto.doNothing();
			}
			break;
		case "BlueMiddletank":
			if(drive.leftEncoder()){	
				auto.middletankBlue();
			}else if(drive.rightEncoder()){
				autoR.middletankBlue();
			}else{
				auto.doNothing();
			}
			break;
		case "BlueRighttank":
			if(drive.leftEncoder()){	
				auto.righttankBlue();
			}else if(drive.rightEncoder()){
				autoR.righttankBlue();
			}else{
				auto.doNothing();
			}
			break;
		case "BlueLefttank":
			if(drive.leftEncoder()){	
				auto.lefttankBlue();
			}else if(drive.rightEncoder()){
				autoR.lefttankBlue();
			}else{
				auto.doNothing();
			}
			break;
		}
	}

/**
* This function is called periodically during operator control
*/
	@Override
	public void teleopPeriodic() {
		driver.driverControls();
		operator.operatorControls();
		SmartDashboard.putNumber("Flywheel RPM", flywheel.flywheelSpeed());
		SmartDashboard.putBoolean("Flywheel Ready", flywheel.flywheelReady(3000));
		SmartDashboard.putBoolean("Have Gear", gear.haveGear);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}