package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.RobotDrive;
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
public class Robot extends IterativeRobot {
	int autoSelected;
	SendableChooser<Number> chooser;
	DriverStation station;
	RobotDrive myDrive;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	DriverVision driverCamera;
	GearMechanism gear;
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
	Compressor compressor;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	Util util;
//	AlexDrive alex;
//	TsarControls tsar;
	RocketScript getNewScript = new RocketScript();
	String[] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	boolean shooting = false;
	// double turnCameraAngle = 0.0;
	boolean runAutoInit = false;
	boolean checkButton = false;
	int startingENCClicks;
	boolean gotStartingENCClicks = false;
	int autoModeSubStep = 0;
	boolean resetGyro = false;
	boolean selectAutoMode = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {
		// Initilization
		station = DriverStation.getInstance();
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel, operator);
		climb = new Climber();
		drive = new DriveTrain();

		driver = new DriverControls(drive, climb);
		operator = new OperatorControls(tank, gear, flywheel, collector);
//		alex = new AlexDrive(drive, climb);
//		tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);

		gyro.reset();
		gyro.setAngleAdjustment(0.00);
		util = new Util();

		myDrive = new RobotDrive(drive.masterRight, drive.masterLeft);
		myDrive.setExpiration(1.0);
		chooser = new SendableChooser<Number>();

		// Basic AutoModes
		chooser.addDefault("Do Nothing", 0);
		chooser.addObject("Cross Baseline", 1);

		// Starting at the Boiler AutoMode
		chooser.addObject("Start at Boiler and Keep Shooting", 2);

		// Just the Gear AutoModes
		chooser.addObject("Middle Gear", 3);
		chooser.addObject("Right Gear", 4);
		chooser.addObject("Left Gear", 5);

//		// Boiler to Gear AutoModes
//		chooser.addObject("Boiler to Closest Gear", 6);
//		chooser.addObject("Red: Boiler to Middle Gear", 7);
//		chooser.addObject("Blue: Boiler to Middle Gear", 8);
//
//		// Gear to Boiler AutoModes
//		chooser.addObject("Red: Middle Gear to Boiler", 9);
//		chooser.addObject("Blue: Middle Gear to Boiler", 10);
//		chooser.addObject("Red: Right Gear to Boiler", 11);
//		chooser.addObject("Blue: Left Gear to Boiler", 12);

		// Hopper to Boiler AutoModes
		chooser.addObject("Red: Hopper to Boiler", 13);
		chooser.addObject("Blue: Hopper to Boiler", 14);	//Note: untuned
		
		chooser.addObject("Right Gear Without Camera", 15);
		chooser.addObject("Left Gear Without Camera", 16);
		
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
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		gyro.reset();
		Timer.delay(0.5);
		drive.masterLeft.setEncPosition(0);
		drive.masterLeft.enable();
		autoModeSubStep = 0;
		System.out.println("Auto selected: " + autoSelected);
		rocketScriptCurrentCount = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("DB/String 3", "Gyro angle: " + gyro.getAngle());
		SmartDashboard.putString("DB/String 4", "Encoder: " + drive.masterLeft.getEncPosition());
		if(!selectAutoMode){
			selectAutoMode = true;
			Scheduler.getInstance().run();
			autoSelected = (int) chooser.getSelected();
			switch (autoSelected) {
			case 0:
				break;
			case 1:
				rocketScriptData = getNewScript.crossBaseline();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 2:
				rocketScriptData = getNewScript.stayAtBoilerAndShoot();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 3:
				rocketScriptData = getNewScript.middleGearNC();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 4:
				rocketScriptData = getNewScript.rightGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 5:
				rocketScriptData = getNewScript.leftGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 6:
				rocketScriptData = getNewScript.boilerToClosestSideGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 7:
				rocketScriptData = getNewScript.boilerToMiddleGearRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 8:
				rocketScriptData = getNewScript.boilerToMiddleGearBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 9:
				rocketScriptData = getNewScript.middleGearToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 10:
				rocketScriptData = getNewScript.middleGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 11:
				rocketScriptData = getNewScript.rightGearToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 12:
				rocketScriptData = getNewScript.leftGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 13:
				rocketScriptData = getNewScript.hopperToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 14:
				rocketScriptData = getNewScript.hopperToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 15:
				rocketScriptData = getNewScript.rightGearNC();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 16:
				rocketScriptData = getNewScript.leftGearNC();
				rocketScriptLength = rocketScriptData.length;
				break;
			}
		}
		if (rocketScriptCurrentCount < rocketScriptLength) {
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
			if (Integer.parseInt(values[0]) == RobotModes.GET_CAMERA_ANGLE) {
				gyro.reset();
				System.out.println("Reset Angle: " + gyro.getAngle());
				String getSocketData;
				getSocketData = util.getCameraAngle();
				String[] socketValues = getSocketData.split("\\*");
				Timer.delay(0.2); // 0.2, orig 0.5
				distanceFromCamera = Double.parseDouble(socketValues[0]);
				angleFromCamera = Double.parseDouble(socketValues[1]);
				autoModeSubStep = 1;
				startingENCClicks = drive.masterLeft.getEncPosition();
				gotStartingENCClicks = false;
				rocketScriptCurrentCount++;
			}

			if (Integer.parseInt(values[0]) == RobotModes.TURN_CAMERA_ANGLE) {
				if (turnWithCamera()) {
					rocketScriptCurrentCount++;
					resetGyro = false;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.GO_CAMERA_DISTANCE) {
				if (cameraDriveStraight())
					rocketScriptCurrentCount++;
				resetGyro = false;
			}

			if (Integer.parseInt(values[0]) == RobotModes.FAST_DRIVE_STRAIGHT) {
				if (fastDriveStraight(0.60, Double.parseDouble(values[1]), Double.parseDouble(values[2]))) { // .77
					gotStartingENCClicks = false;
					gyro.reset();
					rocketScriptCurrentCount++;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.SHOOTING) {
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				System.out.println("Flywheel RPMS " + flywheel.flywheelSpeed());
				if (flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)) {
					shooting = true;
				}
				if (shooting) {
					tank.runAgitator();
				}
				if (shooting) {
					collector.intake(1);
					tank.tankMotorIntoFlywheel(1);
					Timer.delay(Double.parseDouble(values[1]));
					System.out.println("******************************DELAY IS OVER");
					rocketScriptCurrentCount++;
				}

			}
			if (Integer.parseInt(values[0]) == RobotModes.STOP_SHOOTING) {
				flywheel.stopFlywheel();
				collector.stopCollector();
				tank.stopTank();
			}
			if (Integer.parseInt(values[0]) == RobotModes.WAIT_FOR_GEAR) {
				if (gear.checkGear() == false) {
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.WAIT){
				Timer.delay(Double.parseDouble(values[1]));
				rocketScriptCurrentCount++;
			}
			if(Integer.parseInt(values[0]) == RobotModes.SPIN_UP){
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				rocketScriptCurrentCount++;
			}
//			if(Integer.parseInt(values[0]) == RobotModes.ARC_TURN){
//				arcTurn(Double.parseDouble(values[1]), Double.parseDouble(values[2]), Boolean.parseBoolean(values[3]));
//			}
		}
	}

	@Override
	public void teleopInit() {
		flywheel.stopFlywheel();
		collector.stopCollector();
		shooting = false;
		tank.stopTank();
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
		tank.retractAgitator();
		drive.shiftHigh();
		drive.masterRight.setVoltageRampRate(60);
		drive.masterLeft.setVoltageRampRate(60);
		gear.automated = true;
//		alex = new AlexDrive(drive, climb);
//		tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		driver.driverControls();
		operator.operatorControls();
		// tsar.tsarControls();
		// alex.AlexControls();
		SmartDashboard.putNumber("Flywheel RPM ", flywheel.flywheelSpeed());
		SmartDashboard.putBoolean(" Flywheel Ready", flywheel.flywheelReady(Constants.FLYWHEEL_SPEED));
		SmartDashboard.putBoolean(" Have Gear", gear.checkGear());
		SmartDashboard.putBoolean(" High Gear", drive.highGear);
		SmartDashboard.putBoolean(" Automated Gear Flaps", gear.automated);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println("gyro = " + gyro.getAngle());
		System.out.println("gyro, yaw = " + gyro.getYaw());
		// System.out.println("enc = " + drive.masterRight.getEncPosition());
	}
	//Auto Methods
	public boolean turnWithCamera() {
		return fastDriveStraight(0.60, 7.0, angleFromCamera); //was 5 inches
	}
	public boolean cameraDriveStraight() {
		return fastDriveStraight(0.60, distanceFromCamera, angleFromCamera);
	}
	public boolean fastDriveStraight(double speed, double inches, double angleToDrive) {
		if (drive.leftEncoder()) {
			if (gotStartingENCClicks == false) {
				gyro.reset();
				gotStartingENCClicks = true;
				startingENCClicks = drive.masterLeft.getEncPosition();
				Timer.delay(.1);
				System.out.println("Start ENC click value = " + startingENCClicks);
			}
			if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
					.abs(inches * AutoConstants.TICKS_PER_INCH)) {
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);
				System.out.println("Final NavX Angle: " + gyro.getAngle());
				Timer.delay(.1);
				System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
				return true;
			} else {
				if (inches > 0) {
					if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
							.abs(inches * AutoConstants.TICKS_PER_INCH * .60))
						speed = 0.35;
//					drive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * .020), true); // .07
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
				} else {
//					drive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * .020), true); // .07
					myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
				}
			}
			return false;
		} else if (drive.rightEncoder()) {
			if (gotStartingENCClicks == false) {
				System.out.println("Left Encoder Not Working");
				gyro.reset();
				gotStartingENCClicks = true;
				startingENCClicks = drive.masterRight.getEncPosition();
				Timer.delay(.1);
				System.out.println("Start ENC click value = " + startingENCClicks);
			}
			if (Math.abs((double) (drive.masterRight.getEncPosition() - startingENCClicks)) > Math
					.abs(inches * AutoConstants.TICKS_PER_INCH)) {
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);
				System.out.println("Final NavX Angle: " + gyro.getAngle());
				Timer.delay(.1);
				System.out.println("Enc value after speed 0 " + drive.masterRight.getEncPosition());
				return true;
			} else {
				if (inches > 0) {
					if (Math.abs((double) (drive.masterRight.getEncPosition() - startingENCClicks)) > Math
							.abs(inches * AutoConstants.TICKS_PER_INCH * .60))
						speed = 0.35;
//					drive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * .020), true); // .07
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
				} else {
//					drive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * .020), true); // .07
					myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
				}
			}
			return false;
		} else {
			return true;
		}
	}
//	public boolean arcTurn(double middleToLongSide, double middleToShortSide, boolean direction) {//half of the long side of an elipse b is half the short side and c is the robot with
//        double leftArc;
//        double rightArc;
//        double width = 32.0;
//    	width /= 2;
//        double smallA = middleToLongSide - width;
//        double smallB = middleToShortSide - width;
//        double largeA = middleToLongSide + width;
//        double largeB = middleToShortSide + width;
//        if (direction) {
//            rightArc = Math.PI*Math.abs(3*(smallA + smallB)-Math.sqrt((3*smallA+smallB)*(middleToLongSide+smallB)))/4;
//            leftArc = Math.PI*Math.abs(3*(largeA + largeB)-Math.sqrt((3*largeA+largeB)*(largeA+3*largeB)))/4;
//            if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH){
//            	drive.stopDrive();
//            	return true;
//            }else if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() < leftArc*AutoConstants.TICKS_PER_INCH){
//            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
//            	drive.masterRight.set(0);
//            	return false;
//            }else if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() < rightArc*AutoConstants.TICKS_PER_INCH){
//            	drive.masterLeft.set(0);
//            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
//            	return false;
//            }else{
//            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
//            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
//            	return false;
//            }
//        }else{
//            rightArc = Math.PI*Math.abs(3*(largeA + largeB)-Math.sqrt((3*largeA+largeB)*(largeA+3*largeB)))/4;
//            leftArc = Math.PI*Math.abs(3*(smallA + smallB)-Math.sqrt((3*smallA+smallB)*(middleToLongSide+smallB)))/4;
//            if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH){
//            	drive.stopDrive();
//            	return true;
//            }else if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() < leftArc*AutoConstants.TICKS_PER_INCH){
//            	drive.masterRight.set(0);
//            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
//            	return false;
//            }else if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() < rightArc*AutoConstants.TICKS_PER_INCH){
//            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
//            	drive.masterLeft.set(0);
//            	return false;
//            }else{
//            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
//            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
//            	return false;
//            }
//        }
//    }
}