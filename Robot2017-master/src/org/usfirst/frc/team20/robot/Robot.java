package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	TsarControls tsar;
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
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);
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

		// Boiler to Gear AutoModes
		chooser.addObject("Boiler to Closest Gear", 6);
		chooser.addObject("Red: Boiler to Middle Gear", 7);
		chooser.addObject("Blue: Boiler to Middle Gear", 8);

		// Gear to Boiler AutoModes
		chooser.addObject("Red: Middle Gear to Boiler", 9);
		chooser.addObject("Blue: Middle Gear to Boiler", 10);
		chooser.addObject("Red: Right Gear to Boiler", 11);
		chooser.addObject("Blue: Left Gear to Boiler", 12);

		// Hopper to Boiler AutoModes
		chooser.addObject("Red: Hopper to Boiler", 13);
		chooser.addObject("Blue: Hopper to Boiler", 14);

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
				rocketScriptData = getNewScript.middleGear();
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
			}
		}
		if (rocketScriptCurrentCount < rocketScriptLength) {
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
			if (Integer.parseInt(values[0]) == RobotModes.SMART_DRIVE_STRAIGHT) {
				if (resetGyro == false) {
					gyro.reset();
					Timer.delay(.1);
					resetGyro = true;
				}

				if (driveStraight(0.60, (distanceFromCamera * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) - 20,
						angleFromCamera)) {// turnCameraAngle)) {
					resetGyro = false;
					rocketScriptCurrentCount++;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.ROCKET_TURN) {
				if (resetGyro == false) {
					gyro.reset();

					Timer.delay(.1);
					System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQstarting angle is " + gyro.getAngle());
					resetGyro = true;
				}

				if (rocketTurn(Double.parseDouble(values[1]))) {
					rocketScriptCurrentCount++;
					resetGyro = false;
				}

			}

			if (Integer.parseInt(values[0]) == RobotModes.GET_CAMERA_ANGLE) {
				String getSocketData;
				getSocketData = util.getCameraAngle();
				String[] socketValues = getSocketData.split("\\*");
				distanceFromCamera = Double.parseDouble(socketValues[0]);
				angleFromCamera = Double.parseDouble(socketValues[1]);
				autoModeSubStep = 1;
				startingENCClicks = drive.masterLeft.getEncPosition();
				gotStartingENCClicks = false;
				gyro.reset();
				System.out.println("Reset Angle: " + gyro.getAngle());
				rocketScriptCurrentCount++;
				Timer.delay(0.2); // 0.5
			}

			if (Integer.parseInt(values[0]) == RobotModes.TURN_CAMERA_ANGLE) {
				if (rocketTurnCamera()) {
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
		}
	}

	@Override
	public void teleopInit() {
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		flywheel.stopFlywheel();
		collector.stopCollector();
		operator.shooting = false;
		tank.stopTank();
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
		util.shutDownPi();
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		// driver.driverControls();
		// operator.operatorControls();
		System.out.println("Final angle = " + gyro.getAngle());
		tsar.raoControls();
		SmartDashboard.putNumber(" Flywheel RPM", flywheel.flywheelSpeed());
		SmartDashboard.putBoolean("Flywheel Ready ", flywheel.flywheelReady(Constants.FLYWHEEL_SPEED));
		SmartDashboard.putBoolean("Have Gear ", gear.checkGear());
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

	// auto

	public boolean cameraDriveStraight() {
		System.out.println("Angle From Camera: " + angleFromCamera);
		System.out.println("NavX Angle after Reset: " + gyro.getAngle());
		return fastDriveStraight(0.60, distanceFromCamera, angleFromCamera);
	}

	public boolean fastDriveStraight(double speed, double inches, double angleToDrive) {
		if (drive.leftEncoder()) {
			// if (Math.abs((double) drive.masterLeft.getEncPosition() / 4096.0
			// * Math.PI * 4) > Math.abs(inches)
			// * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) {
			if (gotStartingENCClicks == false) {
				gyro.reset();
				gotStartingENCClicks = true;
				startingENCClicks = drive.masterLeft.getEncPosition();
				Timer.delay(.1);
				System.out.println("Start ENC click value = " + startingENCClicks);
			}
			if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
					.abs(inches * Constants.CLICKS_PER_INCH)) {
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);
				System.out.println("Final NavX Angle: " + gyro.getAngle());
				Timer.delay(.1);
				// myDrive.arcadeDrive(0, 0);
				System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
				return true;
			} else {
				if (inches > 0) {
					if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
							.abs(inches * Constants.CLICKS_PER_INCH * .60))
						speed = 0.35;
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
					// Timer.delay(.05);
					// myDrive.arcadeDrive(speed,0);
				} else {
					myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * .020)); // .07
					// Timer.delay(.05);
				}
			}
			return false;
		} else if (drive.rightEncoder()) {
			if (Math.abs((double) (drive.masterRight.getEncPosition() - startingENCClicks)) > Math
					.abs(inches * 667.00)) {
				// if (Math.abs(drive.masterRight.getEncPosition() / 4096 *
				// Math.PI * 4) > Math.abs(inches)
				// * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) {
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);

				System.out.println("Done driving. Enc = " + drive.masterLeft.getEncPosition());
				// myDrive.arcadeDrive(0, 0);
				return true;
			} else {
				if (inches > 0) {
					// drive.masterRight.set(speed);
					// drive.masterLeft.set(-speed);
					myDrive.arcadeDrive(speed, -(gyro.getAngle() * .025)); // 0.07
					// Timer.delay(.05);
				} else {
					myDrive.arcadeDrive(-speed, -(gyro.getAngle() * .025)); // 0.07
					// Timer.delay(.05);
					// drive.masterRight.set(-speed);
					// drive.masterLeft.set(speed);
				}
			}
		}
		return true;
	}

	// You need to run
	public boolean rocketTurnCamera() {
		return fastDriveStraight(0.60, 5.0, angleFromCamera);
	}

	public boolean rocketTurn(double angleToTurn) {
		boolean weAreDone = false;
		if (angleToTurn > 1) {
			System.out.println("Positive angle");
			if ((angleToTurn - gyro.getAngle()) < 1.00) {
				weAreDone = true;
				// myDrive.arcadeDrive(0, 0);
				drive.masterLeft.set(0.00); // .40
				drive.masterRight.set(0.00);
				System.out.println(
						"*******************************navx = " + gyro.getAngle() + "\n Angle to turn " + angleToTurn);

			} else {
				// drive.masterLeft.set(-0.40);//-.40
				// drive.masterRight.set(0.00);
				drive.masterLeft.set(-0.24);
				drive.masterRight.set(-0.24);
				// myDrive.arcadeDrive(0, .55);
			}
		}

		if (angleToTurn < 1) {
			if ((angleToTurn - gyro.getAngle()) > -1.00) {
				weAreDone = true;
				// myDrive.arcadeDrive(0, 0);
				drive.masterLeft.set(0.00); // .40
				drive.masterRight.set(0.00);

				System.out.println(
						"*******************************navx = " + gyro.getAngle() + "\n Angle to turn " + angleToTurn);
			} else {
				// myDrive.arcadeDrive(0, -.55);
				// drive.masterLeft.set(0.00); // .40
				// drive.masterRight.set(0.40);
				drive.masterLeft.set(0.24);
				drive.masterRight.set(0.24);

			}
		}
		return weAreDone;
	}

	public boolean driveStraight(double speed, double inches, double angle) {
		boolean doneDriving = false;
		currentRotationRate = rotateToAngleRate;
		if (drive.leftEncoder()) {
			if (drive.masterLeft.getEncPosition() / 4096 * Math.PI
					* 4 > (inches * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER)) {
				myDrive.arcadeDrive(0, 0);
				doneDriving = true;
			} else {
				myDrive.arcadeDrive(speed, 0);
			}
			return doneDriving;
		} else if (drive.rightEncoder()) {
			if (drive.masterRight.getEncPosition() / 4096 * Math.PI
					* 4 > (inches * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER)) {
				myDrive.arcadeDrive(0, 0);
				doneDriving = true;
			} else {

				myDrive.arcadeDrive(speed, currentRotationRate);
			}
			return doneDriving;
		} else {
			drive.stopDrive();
			return true;
		}
	}

}