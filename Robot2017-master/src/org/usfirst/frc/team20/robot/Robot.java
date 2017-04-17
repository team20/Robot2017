package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	DriverStation station;
	RobotDrive myDrive;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	GearMechanism gear;
	BobbysGearMechanism bobby;
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
	AlexDrive alex;
	TsarControls tsar;
	Compressor compressor;
	// DriverVision cam;
	CircleArcs arc;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	Util util;
	RocketScript getNewScript = new RocketScript();
	String[] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	int startingENCClicks;
	int autoModeSubStep = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	double startTime;
	boolean shooting = false;
	boolean resetGyro = false;
	boolean setStartTime = false;
	boolean waitStartTime = false;
	boolean setStartTimeFlywheel = false;
	boolean gotStartingENCClicks = false;
	boolean setStartTimeShoot = false;
	boolean resetGyroTurn = false;

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
		bobby = new BobbysGearMechanism(operator, collector);
		driver = new DriverControls(drive, climb);
		operator = new OperatorControls(tank, gear, flywheel, collector, bobby);
		arc = new CircleArcs();
		// alex = new AlexDrive(drive, climb);
		// tsar = new TsarControls(drive, climb, tank, gear, flywheel,
		// collector);

		// cam = new DriverVision("cam0", 0);
		// cam.startUSBCamera();

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);

		gyro.reset();
		gyro.setAngleAdjustment(0.00);
		util = new Util();

		myDrive = new RobotDrive(drive.masterRight, drive.masterLeft);
		myDrive.setExpiration(1.0);

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

	public void autonomousInit() { // TODO uncomment gear flap stuff
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		gyro.reset();
		drive.masterLeft.setEncPosition(0);
		drive.masterLeft.enable();
		autoModeSubStep = 0;
		rocketScriptCurrentCount = 0;
		// gear.gearFlapOut();
		boolean button1 = SmartDashboard.getBoolean("DB/Button 0", false);
		boolean button2 = SmartDashboard.getBoolean("DB/Button 1", false);
		boolean button3 = SmartDashboard.getBoolean("DB/Button 2", false);
		boolean button4 = SmartDashboard.getBoolean("DB/Button 3", false);
//		rocketScriptData = getNewScript.testTurningRight();
//		rocketScriptData = getNewScript.testTurningLeft();
//		rocketScriptLength = rocketScriptData.length;
		if (button1) {
			if (button2 && !button3 && !button4) { // middle peg
				rocketScriptData = getNewScript.middleGearTimed();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4) { // right peg
				rocketScriptData = getNewScript.rightGearNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4) { // left peg
				rocketScriptData = getNewScript.leftGearNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (button2 && button3 && !button4) { // side peg to boiler
				rocketScriptData = getNewScript.rightGearToBoilerRedNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && button4) { // 40kPa
				rocketScriptData = getNewScript.hopperToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapIn();
			} else if (button2 && !button3 && button4) { // 10kPa
				rocketScriptData = getNewScript.stayAtBoilerAndShoot();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapIn();
			} else if (button2 && button3 && button4) {
				rocketScriptData = getNewScript.middleGearToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
			}
		} else {
			if (button2 && !button3 && !button4) { // middle peg
				rocketScriptData = getNewScript.middleGearTimed();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4) { // right peg
				rocketScriptData = getNewScript.rightGearNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4) { // left peg
				rocketScriptData = getNewScript.leftGearNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (button2 && button3 && !button4) { // side peg to boiler
				rocketScriptData = getNewScript.leftGearToBoilerBlueNC();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && button4) { // 40kPa
				rocketScriptData = getNewScript.hopperToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapIn();
			} else if (button2 && button3 && button4) {
				rocketScriptData = getNewScript.middleGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
			}
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		if (rocketScriptCurrentCount < rocketScriptLength) {
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
			if (Integer.parseInt(values[0]) == RobotModes.GET_CAMERA_ANGLE) {
				System.out.println("*********************TRYING TO GET ANGLE");
				String getSocketData;
				getSocketData = util.getCameraAngle();
				String[] socketValues = getSocketData.split("\\*");
				Timer.delay(0.2); // 0.2, orig 0.5
				distanceFromCamera = Double.parseDouble(socketValues[0]);
				angleFromCamera = Double.parseDouble(socketValues[1]);
				System.out.println(Double.toString(distanceFromCamera));
				System.out.println(Double.toString(angleFromCamera));
				autoModeSubStep = 1;
				startingENCClicks = drive.masterLeft.getEncPosition();
				gotStartingENCClicks = false;
				rocketScriptCurrentCount++;
			}
			if (Integer.parseInt(values[0]) == RobotModes.GO_CAMERA_DISTANCE) {
				if (cameraDriveStraight()) {
					resetGyro = false;
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.FAST_DRIVE_STRAIGHT) {
				if (fastDriveStraight(Double.parseDouble(values[1]),
						Double.parseDouble(values[2]) - AutoConstants.STOPPING_INCHES, Double.parseDouble(values[3]))) {
					gotStartingENCClicks = false;
					gyro.reset();
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.SHOOTING) {
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				System.out.println("Flywheel RPMS " + flywheel.flywheelSpeed());
				if (!setStartTimeShoot) {
					startTime = Timer.getFPGATimestamp();
					setStartTimeShoot = true;
				}
				if (Timer.getFPGATimestamp() - startTime > 0.8) {
					collector.intake(1);
					tank.tankMotorIntoFlywheel(0.4);
					shooting = true;
				}
				if (shooting) {
					tank.runAgitator();
					System.out.println("***************Running Agitator");
					if (!setStartTimeFlywheel) {
						startTime = Timer.getFPGATimestamp();
						setStartTimeFlywheel = true;
					}
					if (Timer.getFPGATimestamp() - startTime > Double.parseDouble(values[1])) {
						System.out.println("******************************DONE SHOOTING");
						rocketScriptCurrentCount++;
					}
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.STOP_SHOOTING) {
				System.out.println("****************Stopping Shooting");
				flywheel.stopFlywheel();
				collector.stopCollector();
				tank.stopTank();
				shooting = false;
				setStartTimeFlywheel = false;
				rocketScriptCurrentCount++;
			}
			if (Integer.parseInt(values[0]) == RobotModes.WAIT) {
				System.out.println("WAITING");
				if (!waitStartTime) {
					startTime = Timer.getFPGATimestamp();
					waitStartTime = true;
				}
				if (Timer.getFPGATimestamp() - startTime > Double.parseDouble(values[1])) {
					System.out.println("******************************DONE WAITING");
					waitStartTime = false;
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.SPIN_UP) {
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				rocketScriptCurrentCount++;
			}
			if (Integer.parseInt(values[0]) == RobotModes.TIME_DRIVE) {
				if (fastDriveStraightTimer(Double.parseDouble(values[1]), Double.parseDouble(values[2]), true)) {
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.LOW_GEAR) {
				drive.shiftLow();
				rocketScriptCurrentCount++;
			}
			if (Integer.parseInt(values[0]) == RobotModes.ARC_TURN) {
				if (fastDriveArc(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
						Double.parseDouble(values[3]), Boolean.parseBoolean(values[4]))) {
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.TURN) {
				if (turning(0.6, Double.parseDouble(values[1]))) {
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.CAMERA_TURN) {
				if (cameraTurn()) {
					rocketScriptCurrentCount++;
				}
			}
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
		drive.masterRight.setVoltageRampRate(120);
		drive.masterLeft.setVoltageRampRate(120);
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
		SmartDashboard.putString("DB/String 0", "Flywheel Speed");
		SmartDashboard.putString("DB/String 1", "Flywheel Ready?");
		SmartDashboard.putString("DB/String 2", "High Gear?");
		SmartDashboard.putString("DB/String 3", "Collector Jam?");
		SmartDashboard.putString("DB/String 5", Double.toString(flywheel.flywheelSpeed()));
		SmartDashboard.putString("DB/String 6", Boolean.toString(flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)));
		SmartDashboard.putString("DB/String 7", Boolean.toString(drive.highGear));
		SmartDashboard.putString("DB/String 8", Boolean.toString(collector.collectorJam()));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println("Left: " + drive.masterLeft.getEncPosition());
		System.out.println(" Right: " + drive.masterRight.getEncPosition());
	}

	// Auto Methods
	public boolean cameraDriveStraight() {
		System.out.println("(((((((((((((((((((((((((((((( " + distanceFromCamera);
		return fastDriveStraight(0.6, distanceFromCamera, angleFromCamera);
	}

	public boolean cameraTurn() {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + angleFromCamera);
		return turning(0.6, angleFromCamera);
	}

	public boolean turning(double speed, double angleToDrive) {
		double pValue = 0;
		if(angleToDrive < 0){
			pValue = Constants.DRIVING_P_LEFT;
		} else {
			pValue = Constants.DRIVING_P_RIGHT;
		}
		if (!resetGyroTurn) {
			gyro.reset();
			resetGyroTurn = true;
			startTime = Timer.getFPGATimestamp();
		}
		if (Math.abs(gyro.getAngle() - angleToDrive) < Constants.TURNING_DEADBAND) {
			System.out.println("*************************************HIT TURNING DEADBAND");
//			if (!setStartTime) {
//				startTime = Timer.getFPGATimestamp();
//				setStartTime = true;
//			}
//			if (Timer.getFPGATimestamp() - startTime > 0.1) {
//				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&inside the 0.3 second");
//				resetGyroTurn = false;
//				setStartTime = false;
				return true;
//			}
		}
		myDrive.arcadeDrive(0.0, -((gyro.getAngle() - angleToDrive) * pValue));
		return false;
	}

	public boolean fastDriveStraightTimer(double speed, double howMuchTime, boolean withGyro) {
		if (!setStartTime) {
			startTime = Timer.getFPGATimestamp();
			setStartTime = true;
			gyro.reset();
		}
		System.out.println("Time is: " + (Timer.getFPGATimestamp() - startTime));
		if (Timer.getFPGATimestamp() - startTime < howMuchTime) {
			if (Math.abs(speed) > 0.00) {
				if (withGyro)
					myDrive.arcadeDrive(speed, -(gyro.getAngle() * Constants.DRIVING_P));
				else // no Gyro
					myDrive.arcadeDrive(speed, 0);
			}
		} else {
			myDrive.arcadeDrive(0.0, 0);
			setStartTime = false;
			return true;
		}
		return false;
	}

	public boolean fastDriveStraight(double speed, double inches, double angleToDrive) {
		if (gotStartingENCClicks == false) {
			gyro.reset();
			gotStartingENCClicks = true;
			startingENCClicks = drive.masterLeft.getEncPosition();
			System.out.println("Start ENC click value = " + startingENCClicks);
		}
		if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
				.abs(inches * AutoConstants.TICKS_PER_INCH)) {
			drive.masterLeft.set(0.00);
			drive.masterRight.set(0.00);
			System.out.println("Final NavX Angle: " + gyro.getAngle());
			System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
			return true;
		} else {
			if (inches > 0) {
				if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) < Math
						.abs(inches * AutoConstants.TICKS_PER_INCH * .60))
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			} else {
				myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}

	public boolean fastDriveArc(double speed, double radius, double degrees, boolean direction) {
		if (gotStartingENCClicks == false) {
			gyro.reset();
			gotStartingENCClicks = true;
			arc.createArc(radius, degrees, direction);
			radius = arc.getLeftArc();
			System.out.println("Inches " + radius);
			startingENCClicks = drive.masterLeft.getEncPosition();
			System.out.println("Start ENC click value = " + startingENCClicks);
		}
		if (arc.leftDone((drive.masterLeft.getEncPosition() - startingENCClicks) / AutoConstants.TICKS_PER_INCH)) {
			drive.masterLeft.set(0.00);
			drive.masterRight.set(0.00);
			System.out.println("Final NavX Angle: " + gyro.getAngle());
			System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
			return true;
		} else {
			double angleToDrive = arc.getLeftAngle(
					(drive.masterLeft.getEncPosition() - startingENCClicks) / AutoConstants.TICKS_PER_INCH);
			if (radius > 0) {
				if (!arc.leftDone(
						(drive.masterLeft.getEncPosition() - startingENCClicks) / AutoConstants.TICKS_PER_INCH)) {
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
				}
			} else {
				myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}
}