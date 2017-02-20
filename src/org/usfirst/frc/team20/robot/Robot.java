package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.Compressor;
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
	DriverVision frontCamera;
	DriverVision backCamera;
	GearMechanism gear;
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
//	TsarControls tsar;
	Compressor compressor;
	double rotateToAngleRate;
	RaoControls rao;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		//Initilization
		station = DriverStation.getInstance();
		drive = new DriveTrain(vision);
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel, operator);
		climb = new Climber();
		driver = new DriverControls(drive, climb, functions);
		operator = new OperatorControls(tank, gear, flywheel, vision, collector);
		compressor = new Compressor();
		rao = new RaoControls(drive,climb,tank,gear,flywheel,vision,collector);
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);
//		tsar = new TsarControls(drive, climb, tank, gear, flywheel, vision, collector);
//		frontCamera = new DriverVision("Front Camera", 0);
//		frontCamera.startUSBCamera();
//		backCamera = new DriverVision("Back Camera", 1);
//		backCamera.startUSBCamera();
		
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
		
		//Hopper to Boiler AutoModes
		chooser.addObject("Red: Hopper to Boiler", "RedHopperBoiler");
		chooser.addObject("Blue: Hopper to Boiler", "BlueHopperBoiler");

		//Gear to Hopper AutoModes
		chooser.addObject("Red: Middle Gear to Hopper", "RedMiddleHopper");
		chooser.addObject("Red: Right Gear to Hopper", "RedRightHopper");
		chooser.addObject("Red: Left Gear to Hopper", "RedLeftHopper");
		chooser.addObject("Blue: Middle Gear to Hopper", "BlueMiddleHopper");
		chooser.addObject("Blue: Right Gear to Hopper", "BlueRightHopper");
		chooser.addObject("Blue: Left Gear to Hopper", "BlueLeftHopper");

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
	public void autonomousPeriodic() {
//		auto.rightPeg();
//		System.out.println("Encoder Value" + drive.masterLeft.getEncPosition()/1024*Math.PI*4);
//		Scheduler.getInstance().run();
//		switch (autoSelected) {
//		case "Auto1":
//			System.out.println("Running Auto One");
//			auto.auto1();
//			break;
//			
//		//Basic AutoModes
//		case "DoNothing":
////			System.out.println("Do Nothing");
////			auto.doNothing();
//			break;	
//		case "CrossBaseline":
//			if(drive.leftEncoder()){
//				auto.crossBaseline();
//			}else if(drive.rightEncoder()){
//				autoR.crossBaseline();
//			}else{
//				auto.doNothing();
//			}
//			break;
//
//		//Start at the Boiler AutoMode
//		case "StartBoiler":
//			System.out.println("Start at Boiler");
//			if(drive.leftEncoder()){	
//				auto.startBoiler();
//			}else if(drive.rightEncoder()){
//				autoR.startBoiler();
//			}else{
//				auto.doNothing();
//			}
//			break;
//			
//		//Just the Gear AutoModes
//		case "MiddleGear":
//			if(drive.leftEncoder()){
//				auto.middlePeg();
//			}else if(drive.rightEncoder()){
//				autoR.middlePeg();
//			}else{
//				auto.doNothing();
//			}
//			break;		
// 		case "RightGear":
//			if(drive.leftEncoder()){
// 				auto.rightPeg();
//			}else if(drive.rightEncoder()){
//				autoR.rightPeg();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "LeftGear":
//			if(drive.leftEncoder()){	
//				auto.leftPeg();
//			}else if(drive.rightEncoder()){
//				autoR.leftPeg();
//			}else{
//				auto.doNothing();
//			}
//			break;
//
//		//AutoMode That Goes From the Boiler to a Gear
//		case "BoilerSideGear":
//			if(drive.leftEncoder()){	
//				auto.boilerToSidePeg();
//			}else if(drive.rightEncoder()){
//				autoR.boilerToSidePeg();
//			}else{
//				auto.doNothing();
//			}
//			break;
//
//		//AutoModes That Go From a Gear to the Boiler
//		case "RedMiddleBoiler":
//			if(drive.leftEncoder()){	
//				auto.middleBoilerRed();
//			}else if(drive.rightEncoder()){
//				autoR.middleBoilerRed();
//			}else{
//				auto.doNothing();
//			}
//			break;			
//		case "RedRightBoiler":
//			if(drive.leftEncoder()){	
//				auto.rightBoilerRed();
//			}else if(drive.rightEncoder()){
//				autoR.rightBoilerRed();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "RedLeftBoiler":
//			if(drive.leftEncoder()){	
//				auto.leftBoilerRed();
//			}else if(drive.rightEncoder()){
//				autoR.leftBoilerRed();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "BlueMiddleBoiler":
//			if(drive.leftEncoder()){	
//				auto.middleBoilerBlue();
//			}else if(drive.rightEncoder()){
//				autoR.middleBoilerBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;			
//		case "BlueRightBoiler":
//			if(drive.leftEncoder()){	
//				auto.rightBoilerBlue();
//			}else if(drive.rightEncoder()){
//				autoR.rightBoilerBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "BlueLeftBoiler":
//			if(drive.leftEncoder()){	
//				auto.leftBoilerBlue();
//			}else if(drive.rightEncoder()){
//				autoR.leftBoilerBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//			
//		//AutoModes That Go From the Hopper to the Boiler
//		case "RedHopperBoiler":
//			if(drive.leftEncoder()){	
//				auto.HopperBoilerRed();
//			}else if(drive.rightEncoder()){
//				autoR.HopperBoilerRed();
//			}else{
//				auto.doNothing();
//			}
//			break;			
//		case "BlueHopperBoiler":
//			if(drive.leftEncoder()){	
//				auto.HopperBoilerBlue();
//			}else if(drive.rightEncoder()){
//				autoR.HopperBoilerBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//			
//		//AutoModes That Place a Gear Then Go to the Hopper
//		case "RedMiddleHopper":
//			if(drive.leftEncoder()){	
//				auto.middleHopperRed();
//			}else if(drive.rightEncoder()){
//				autoR.middleHopperRed();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "RedRightHopper":
//			if(drive.leftEncoder()){	
//				auto.rightHopperRed();
//			}else if(drive.rightEncoder()){
//				autoR.rightHopperRed();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "RedLeftHopper":
//			if(drive.leftEncoder()){	
//				auto.leftHopperRed();
//			}else if(drive.rightEncoder()){
//				autoR.leftHopperRed();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "BlueMiddleHopper":
//			if(drive.leftEncoder()){	
//				auto.middleHopperBlue();
//			}else if(drive.rightEncoder()){
//				autoR.middleHopperBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "BlueRightHopper":
//			if(drive.leftEncoder()){	
//				auto.rightHopperBlue();
//			}else if(drive.rightEncoder()){
//				autoR.rightHopperBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		case "BlueLeftHopper":
//			if(drive.leftEncoder()){	
//				auto.leftHopperBlue();
//			}else if(drive.rightEncoder()){
//				autoR.leftHopperBlue();
//			}else{
//				auto.doNothing();
//			}
//			break;
//		}
	}

	public void teleopInit() {
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
		System.out.println("Teleop Init");
	}
	
/**
* This function is called periodically during operator control
*/
	
	
	public void teleopPeriodic() {
		//driver.driverControls();
		//operator.operatorControls();
		rao.raoControls();
//		if(driver.driverJoy.modeButton){
//			tsar.tsarControls();
//		}
		SmartDashboard.putNumber(" Flywheel RPM", flywheel.flywheelSpeed());
		SmartDashboard.putBoolean("Flywheel Ready ", flywheel.flywheelReady(Constants.FLYWHEEL_SPEED));
		SmartDashboard.putBoolean("Have Gear ", gear.checkGear());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		
	}
}