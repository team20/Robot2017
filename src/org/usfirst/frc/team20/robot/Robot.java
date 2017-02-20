package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
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
public class Robot extends IterativeRobot implements PIDOutput {
	String autoSelected;
	SendableChooser<String> chooser;
	DriverStation station;
	RobotDrive myDrive;
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
	Compressor compressor;
	PIDController turnController;
	AHRS gyro;
	Util util;
//	TsarControls tsar;
	RocketScript getNewScript = new RocketScript();
	String [] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	boolean shooting = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {
		//Initilization
		station = DriverStation.getInstance();
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel, operator);
		climb = new Climber();
		driver = new DriverControls(drive, climb);
		operator = new OperatorControls(tank, gear, flywheel, collector);
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);
//		driverCamera = new DriverVision("Driver Camera", 0);
//		driverCamera.startUSBCamera();
		
		gyro = new AHRS(SerialPort.Port.kMXP);
		util = new Util();
		myDrive = new RobotDrive(drive.masterRight, drive.masterLeft);
		myDrive.setExpiration(1.0);
		turnController = new PIDController(Constants.NavX_P, Constants.NavX_I, Constants.NavX_D, Constants.NavX_F, gyro, this);
		turnController.setInputRange(-180.0f,  180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(Constants.NavX_Tolerance_Degrees);
		turnController.setContinuous(true);
		Timer.delay(0.05);
		
		chooser = new SendableChooser<String>();
		
		//Basic AutoModes
		chooser.addDefault("Do Nothing", "DoNothing");
		chooser.addObject("Cross Baseline", "CrossBaseline");
		
		//Starting at the Boiler AutoMode
		chooser.addObject("Start at Boiler and Keep Shooting", "StayAtBoilerAndShoot");
		
		//Just the Gear AutoModes
		chooser.addObject("Middle Gear", "MiddleGear");
		chooser.addObject("Right Gear", "RightGear");
		chooser.addObject("Left Gear", "LeftGear");
		
		//Boiler to Gear AutoModes
		chooser.addObject("Boiler to Closest Gear", "BoilerToClosestSideGear");
		chooser.addObject("Red: Boiler to Middle Gear", "BoilerToMiddleGearRed");
		chooser.addObject("Blue: Boiler to Middle Gear", "BoilerToMiddleGearBlue");

		//Gear to Boiler AutoModes
		chooser.addObject("Red: Middle Gear to Boiler", "MiddleGearToBoilerRed");
		chooser.addObject("Blue: Middle Gear to Boiler", "MiddleGearToBoilerBlue");
		chooser.addObject("Red: Right Gear to Boiler", "RightGearToBoilerRed");
		chooser.addObject("Blue: Left Gear to Boiler", "LeftGearToBoilerBlue");

		//Hopper to Boiler AutoModes
		chooser.addObject("Red: Hopper to Boiler", "HopperToBoilerRed");
		chooser.addObject("Blue: Hopper to Boiler", "HopperToBoilerBlue");

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
		Scheduler.getInstance().run();
		System.out.println("Auto selected: " + autoSelected);
		switch (autoSelected) {
		case "DoNothing":
			rocketScriptData = getNewScript.crossBaseline();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "CrossBaseline":
			rocketScriptData = getNewScript.crossBaseline();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "StayAtBoilerAndShoot":
			rocketScriptData = getNewScript.stayAtBoilerAndShoot();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "MiddleGear":
			rocketScriptData = getNewScript.middleGear();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "RightGear":
			rocketScriptData = getNewScript.rightGear();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "LeftGear":
			rocketScriptData = getNewScript.leftGear();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "BoilerToClosestSideGear":
			rocketScriptData = getNewScript.boilerToClosestSideGear();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "BoilerToMiddleGearRed":
			rocketScriptData = getNewScript.boilerToMiddleGearRed();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "BoilerToMiddleGearBlue":
			rocketScriptData = getNewScript.boilerToMiddleGearBlue();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "MiddleGearToBoilerRed":
			rocketScriptData = getNewScript.middleGearToBoilerRed();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "MiddleGearToBoilerBlue":
			rocketScriptData = getNewScript.middleGearToBoilerBlue();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "RightGearToBoilerRed":
			rocketScriptData = getNewScript.rightGearToBoilerRed();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "LeftGearToBoilerBlue":
			rocketScriptData = getNewScript.leftGearToBoilerBlue();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "HopperToBoilerRed":
			rocketScriptData = getNewScript.hopperToBoilerRed();
			rocketScriptLength = rocketScriptData.length;
			break;
		case "HopperToBoilerBlue":
			rocketScriptData = getNewScript.hopperToBoilerBlue();
			rocketScriptLength = rocketScriptData.length;
			break;
		}			
	}


	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		if (rocketScriptCurrentCount < rocketScriptLength) {
			// System.out.println("********running rocketScript counter");
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
			System.out.println("Value 1: " + values[0] + " Value 2: " + values[1]);
			if (Integer.parseInt(values[0]) == RobotModes.SMART_DRIVE_STRAIGHT) {
				double fudgeFactor = Double.parseDouble(values[1]);
				if (driveStraight(0.65, distanceFromCamera, angleFromCamera + fudgeFactor)) {
					rocketScriptCurrentCount++;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.SMART_TURN_ANGLE) {
				System.out.println("Smart Turn Angle turning" + angleFromCamera);
				if (TurnAngle(angleFromCamera)) {
					drive.masterLeft.setEncPosition(0);
					rocketScriptCurrentCount++;
				}

			}

			if (Integer.parseInt(values[0]) == RobotModes.GET_CAMERA_ANGLE) {
				String getSocketData;
				gyro.reset();
				Timer.delay(0.5);
				getSocketData = util.getCameraAngle();
				String[] socketValues = getSocketData.split("\\*");
				distanceFromCamera = Double.parseDouble(socketValues[0]);
				angleFromCamera = Float.parseFloat(socketValues[1]);
				System.out.println("Distance from camera: " + distanceFromCamera);
				System.out.println("Angle from camera: " + angleFromCamera);
				turnController.reset();
				turnController.setSetpoint(angleFromCamera);
				turnController.enable();
				rocketScriptCurrentCount++;
			}

			if (Integer.parseInt(values[0]) == RobotModes.RAW_TURN_ANGLE) {
				if (turnRoughAngle(Double.parseDouble(values[1]))) {
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.RAW_DRIVE_STRAIGHT) {
				if (dumbDriveStraight(1.0, Double.parseDouble(values[1]), 6.5)) {
					drive.masterLeft.setEncPosition(0);
					gyro.reset();
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.SHOOTING) {
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				if (flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)) {
					collector.intake(1);
					tank.tankMotorIntoFlywheel(1);
					shooting = true;
				}
				if (shooting) {
					tank.runAgitator();
					Timer.delay(Double.parseDouble(values[1]));
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.STOP_SHOOTING){
				flywheel.stopFlywheel();
				collector.stopCollector();
				tank.stopTank();
			}
		}
	}

	@Override
	public void teleopInit() {
		myDrive.free();
		turnController.disable();
		flywheel.stopFlywheel();
		collector.stopCollector();
		shooting = false;
		tank.stopTank();
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
		drive = new DriveTrain();
	}
	
/**
* This function is called periodically during operator control
*/
	
	@Override	
	public void teleopPeriodic() {
		driver.driverControls();
		operator.operatorControls();
		SmartDashboard.putNumber(" Flywheel RPM", flywheel.flywheelSpeed());
		SmartDashboard.putBoolean("Flywheel Ready ", flywheel.flywheelReady(Constants.FLYWHEEL_SPEED));
		SmartDashboard.putBoolean("Have Gear ", gear.checkGear());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}

	private boolean TurnAngle(double cameraAngle){
		boolean doneTurning = false;
		currentRotationRate = rotateToAngleRate;
		if (Math.abs(cameraAngle - gyro.getAngle()) < .6 && Math.abs(currentRotationRate) < .5) {
			currentRotationRate = 0;
			myDrive.arcadeDrive(0.0, 0);
			doneTurning = true;
		} else {
			try {
				myDrive.arcadeDrive(0.0, currentRotationRate);
			} catch (RuntimeException ex) {
				DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
			}
		}
		return doneTurning;
	}

	public boolean turnRoughAngle(double turnAngle){
		if(turnAngle < 0){
			if(Math.abs(gyro.getYaw()-turnAngle) < 5){
				myDrive.arcadeDrive(0,0);
				return true;
			}else{
				myDrive.arcadeDrive(0,-.5);
			}
		}else{
			if(Math.abs(gyro.getYaw()-turnAngle)<5){
				myDrive.arcadeDrive(0, 0);
				return true;
			}else{
				myDrive.arcadeDrive(0,.5);
			}
		}
		return false;
	}
	
	public boolean dumbDriveStraight(double speed, double inches, double multiplier){
		if(Math.abs(drive.masterLeft.getEncPosition()/4096 *Math.PI*4) > Math.abs(inches * multiplier)){
			myDrive.arcadeDrive(0, 0);
			return true;
		}else{
			if(inches > 0) {
				drive.masterRight.set(speed);
				drive.masterLeft.set(-speed);
			}
			else {
				drive.masterRight.set(-speed);
				drive.masterLeft.set(speed);
			}
		}
		return false;
	}
	public boolean driveStraight(double speed, double inches, double angle){
		boolean doneDriving = false;
		currentRotationRate = rotateToAngleRate;
		if(drive.leftEncoder()){
			if(drive.masterLeft.getEncPosition()/4096*Math.PI*4 > (inches*AutoConstants.DRIVE_STRAIGHT_MULTIPLIER)){
				 myDrive.arcadeDrive(0, 0);
	  			doneDriving = true;
			}
			else{
				  turnController.setSetpoint(angle);
				  turnController.enable();
				  myDrive.arcadeDrive(speed, currentRotationRate);
			}
			return doneDriving;			
		}else if(drive.rightEncoder()){
			if(drive.masterRight.getEncPosition()/4096*Math.PI*4 > (inches*AutoConstants.DRIVE_STRAIGHT_MULTIPLIER)){
				 myDrive.arcadeDrive(0, 0);
	  			doneDriving = true;
			}
			else{
				  turnController.setSetpoint(angle);
				  turnController.enable();
				  myDrive.arcadeDrive(speed, currentRotationRate);
			}
			return doneDriving;
		}else{
			drive.stopDrive();
			return true;
		}
	}
	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output*0.8;
	}
}