package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
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
	RobotDrive myDrive;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	GearMechanism gear;
	GearPickup carlos;
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
	AlexDrive alex;
	// TsarControls tsar;
	Compressor compressor;
	// DriverVision cam;
	CircleArcs arc;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	RocketScript getNewScript;
	String[] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	int startingENCClicks;
	int autoModeSubStep = 0;
	double rotateToAngleRate, currentRotationRate;
	double startTime;
	double nominalVoltage = Constants.NOMINAL_VOLTAGE;
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
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel, operator);
		climb = new Climber();
		drive = new DriveTrain();
		carlos = new GearPickup();
		driver = new DriverControls(drive, climb);
		operator = new OperatorControls(tank, gear, flywheel, collector, carlos);
		arc = new CircleArcs();
		getNewScript = new RocketScript();
		// alex = new AlexDrive(drive, climb);
		// tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);

		// cam = new DriverVision("cam0", 0);
		// cam.startUSBCamera();

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);

		gyro.reset();
		gyro.setAngleAdjustment(0.00);

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
//		rocketScriptData = getNewScript.testTurningRight60();
//		rocketScriptData = getNewScript.testTurningRight90();
//		rocketScriptData = getNewScript.testTurningRight105();
//		rocketScriptData = getNewScript.testTurningRight170();
//		rocketScriptData = getNewScript.testTurningLeft60();
//		rocketScriptData = getNewScript.testTurningLeft90();
//		rocketScriptData = getNewScript.testTurningLeft105();
//		rocketScriptData = getNewScript.testTurningLeft170();
//		rocketScriptLength = rocketScriptData.length;			//REMEMBER TO UNCOMMENT THIS WHEN TESTING
		boolean button1 = SmartDashboard.getBoolean("DB/Button 0", false);
		boolean button2 = SmartDashboard.getBoolean("DB/Button 1", false);
		boolean button3 = SmartDashboard.getBoolean("DB/Button 2", false);
		boolean button4 = SmartDashboard.getBoolean("DB/Button 3", false);
		double neutralZone = SmartDashboard.getNumber("DB/Slider 0", 0.0);
		if (button1) {
			if (button2 && !button3 && !button4 && neutralZone == 0.0) { // middle peg
				rocketScriptData = getNewScript.middleGearNeutralZoneRed();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (button2 && !button3 && !button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.middleGear();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4 && neutralZone == 0.0) { // right peg
				rocketScriptData = getNewScript.rightGearNeutralZone();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.rightGear();
				rocketScriptLength = rocketScriptData.length;				
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4 && neutralZone == 0.0) { // left peg
				rocketScriptData = getNewScript.leftGearNeutralZone();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.leftGear();
				rocketScriptLength = rocketScriptData.length;				
				// gear.gearFlapOut();
			} else if (button2 && button3 && !button4) { // side peg to boiler
				rocketScriptData = getNewScript.rightGearToBoilerRed();
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
				// gear.gearFlapOut();
			}
		} else {
			if (button2 && !button3 && !button4 && neutralZone == 0.0) { // middle peg
				rocketScriptData = getNewScript.middleGearNeutralZoneBlue();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (button2 && !button3 && !button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.middleGear();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4 && neutralZone == 0.0) { // right peg
				rocketScriptData = getNewScript.rightGearNeutralZone();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && !button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.rightGear();
				rocketScriptLength = rocketScriptData.length;				
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4 && neutralZone == 0.0) { // left peg
				rocketScriptData = getNewScript.leftGearNeutralZone();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && !button3 && button4 && neutralZone != 0.0){
				rocketScriptData = getNewScript.leftGear();
				rocketScriptLength = rocketScriptData.length;				
				// gear.gearFlapOut();
			} else if (button2 && button3 && !button4) { // side peg to boiler
				rocketScriptData = getNewScript.leftGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			} else if (!button2 && button3 && button4) { // 40kPa
				rocketScriptData = getNewScript.hopperToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapIn();
			} else if (button2 && button3 && button4) {
				rocketScriptData = getNewScript.middleGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				// gear.gearFlapOut();
			}
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
//		double speed = Double.parseDouble(SmartDashboard.getString("DB/String 0"));
//		drive.turnLeft(speed);
		if (rocketScriptCurrentCount < rocketScriptLength) {
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
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
//				if (!setStartTimeShoot) {
//					startTime = Timer.getFPGATimestamp();
//					setStartTimeShoot = true;
//				}
//				if (Timer.getFPGATimestamp() - startTime > 0.8) {
					collector.intake(1);
					tank.tankMotorIntoFlywheel(0.4);
					shooting = true;
//				}
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
				System.out.println("Turning");
				if (turn(Double.parseDouble(values[1]))) {
					setStartTime = false;
					resetGyroTurn = false;
					rocketScriptCurrentCount++;
				}
			}
		}
	}

	@Override
	public void teleopInit() {
		//gear.gearFlapOut();
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
		//alex.AlexControls();
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
	public void testInit() {

	}

	@Override
	public void testPeriodic() {
		System.out.println("Left: " + drive.masterLeft.getEncPosition());
		System.out.println(" Right: " + drive.masterRight.getEncPosition());
	}

	// Auto Methods
	public boolean turn(double angleToDrive) {
		double pValue = 0;
		if (gyro.getAngle() < angleToDrive) {
			pValue = Constants.TURN_P_RIGHT;
		} else {
			pValue = Constants.TURN_P_LEFT;
		}
		if (!resetGyroTurn) {
			gyro.reset();
			resetGyroTurn = true;
		}
		if (Math.abs(gyro.getAngle() - angleToDrive) < Constants.TURNING_DEADBAND) {
			System.out.println("*************************************HIT TURNING DEADBAND");
			if (!setStartTime) {
				startTime = Timer.getFPGATimestamp();
				setStartTime = true;
			}
			if (Timer.getFPGATimestamp() - startTime > 0.3) {
				System.out.println("#######################################################HIT TUNE TIME");
				if (Math.abs(gyro.getAngle() - angleToDrive) < Constants.TURNING_DEADBAND) {
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&done turning");
					drive.stopDrive();
					return true;
				} else {
					setStartTime = false;
				}
			}
		}
		System.out.println("*****************************TURNINGGGGGGGGGGGGGGGGGGGGGG");
		arcadeTurn(-((gyro.getAngle() - angleToDrive) * pValue));
		return false;
	}

	public void arcadeTurn(double rotateValue) {
		double leftMotorSpeed, rightMotorSpeed;
		if(rotateValue > 1.0){
			rotateValue = 1.0;
		} else if (rotateValue < -1.0){
			rotateValue = -1.0;
		}
		if (rotateValue >= 0.0) {
			rotateValue = rotateValue * rotateValue;
		} else {
			rotateValue = -(rotateValue * rotateValue);
		}
		if (rotateValue > 0.0) {
			leftMotorSpeed = 0.0;
			rightMotorSpeed = rotateValue;
		} else {
			leftMotorSpeed = -rotateValue;
			rightMotorSpeed = 0.0;
		}
		if(leftMotorSpeed > 1.0){
			leftMotorSpeed = 1.0;
		} else if (leftMotorSpeed < -1.0){
			leftMotorSpeed = -1.0;
		} else if (Math.abs(leftMotorSpeed) < Constants.NOMINAL_VOLTAGE){
			if (leftMotorSpeed > 0.0){
				leftMotorSpeed = nominalVoltage;
			} else {
				leftMotorSpeed = -nominalVoltage;
			}
		}
		if(rightMotorSpeed > 1.0){
			rightMotorSpeed = 1.0;
		} else if (rightMotorSpeed < -1.0){
			rightMotorSpeed = -1.0;
		} else if (Math.abs(rightMotorSpeed) < Constants.NOMINAL_VOLTAGE){
			if (rightMotorSpeed > 0.0){
				rightMotorSpeed = nominalVoltage;
			} else {
				rightMotorSpeed = -nominalVoltage;
			}
		}
		System.out.println("*********************** " + leftMotorSpeed);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + rightMotorSpeed);
		drive.masterLeft.set(leftMotorSpeed);
		drive.masterRight.set(-rightMotorSpeed);
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
				else
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
				if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) < 
						Math.abs(inches * AutoConstants.TICKS_PER_INCH))
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