package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import javax.script.*;
import java.io.FileReader;

public class Robot extends IterativeRobot {
	ScriptEngine js;
	Invocable autoPeriodic;
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
	int rocketScriptCurrentCount, rocketScriptLength = 0, startingENCClicks, autoModeSubStep = 0, startingENCClicksLeft = 0, startingENCClicksRight = 0;
	double rotateToAngleRate, currentRotationRate, startTime;
	double nominalVoltage = Constants.NOMINAL_VOLTAGE;
	boolean shooting = false, resetGyro = false, setStartTime = false, waitStartTime = false, setStartTimeFlywheel = false,
			gotStartingENCClicks = false, setStartTimeShoot = false, resetGyroTurn = false;
//	Logger logger = new Logger();
	boolean beenEnabled = false, socket = false;

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
		//driver = new DriverControls(drive, climb);
		operator = new OperatorControls(tank, gear, flywheel, collector, carlos);
		arc = new CircleArcs();
		getNewScript = new RocketScript();
		alex = new AlexDrive(drive, climb);

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);

		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);

		gyro.reset();
		gyro.setAngleAdjustment(0.00);

		myDrive = new RobotDrive(drive.masterRight, drive.masterLeft);
		myDrive.setExpiration(1.0);

		try {
			js = new ScriptEngineManager().getEngineByName("nashorn");
			js.put("flywheel", flywheel);
			js.put("collector", collector);
			js.put("tank", tank);
			js.put("gear", gear);
			js.put("climb", climb);
			js.put("drive", drive);
			js.put("carlos", carlos);
			js.eval(new FileReader("auto.js"));
			auto = (Invocable) js;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disabledInit() {
	}

	public void autonomousInit() {
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		gyro.reset();
		drive.masterLeft.setEncPosition(0);
		drive.masterLeft.enable();
		autoModeSubStep = 0;
		rocketScriptCurrentCount = 0;
		gear.gearFlapOut();
		rocketScriptData = getNewScript.splineTest1();
		rocketScriptLength = rocketScriptData.length;

	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		try {
			auto.invokeFunction("periodic");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void teleopInit() {
		gear.gearFlapOut();
		flywheel.stopFlywheel();
		collector.stopCollector();
		shooting = false;
		tank.stopTank();
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
		tank.retractAgitator();
		drive.shiftHigh();
		drive.masterRight.setVoltageRampRate(120);
		drive.masterLeft.setVoltageRampRate(120);

//		if(!socket){
//			logger.startSocket(); socket = true;
//		}
//		beenEnabled = true;
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
//		logger.log();
//		String speed = SmartDashboard.getString("DB/String 0", "4700");
//		Constants.FLYWHEEL_SPEED = Double.parseDouble(speed);
//		String f = SmartDashboard.getString("DB/String 1", "0.027");
//		Constants.FLYWHEEL_F = Double.parseDouble(speed);
		//driver.driverControls();
		operator.operatorControls();
		// tsar.tsarControls();
		alex.AlexControls();
		SmartDashboard.putString("DB/String 0", "Flywheel Speed");
		SmartDashboard.putString("DB/String 1", "Flywheel Ready?");
		SmartDashboard.putString("DB/String 2", "High Gear?");
		SmartDashboard.putString("DB/String 3", "Collector Jam?");
		SmartDashboard.putString("DB/String 4", "Carlos Up?");
		SmartDashboard.putString("DB/String 5", Double.toString(flywheel.flywheelSpeed()));
		SmartDashboard.putString("DB/String 6", Boolean.toString(flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)));
		SmartDashboard.putString("DB/String 7", Boolean.toString(drive.highGear));
		SmartDashboard.putString("DB/String 8", Boolean.toString(collector.collectorJam()));
		SmartDashboard.putString("DB/String 9", Boolean.toString(carlos.up));
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
			nominalVoltage = Constants.NOMINAL_VOLTAGE;
		}
		if (Math.abs(gyro.getAngle() - angleToDrive) < Constants.TURNING_DEADBAND) {
			System.out.println("*************************************HIT TURNING DEADBAND");
			if (!setStartTime) {
				startTime = Timer.getFPGATimestamp();
				setStartTime = true;
				nominalVoltage = Constants.NOMINAL_VOLTAGE;
			}
			if (Timer.getFPGATimestamp() - startTime > 0.5) {
				System.out.println("#######################################################HIT TUNE TIME");
				nominalVoltage = 0;
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
		if (rotateValue > 1.0) {
			rotateValue = 1.0;
		} else if (rotateValue < -1.0) {
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
		if (leftMotorSpeed > 1.0) {
			leftMotorSpeed = 1.0;
		} else if (leftMotorSpeed < -1.0) {
			leftMotorSpeed = -1.0;
		} else if (Math.abs(leftMotorSpeed) < Constants.NOMINAL_VOLTAGE) {
			if (leftMotorSpeed > 0.0) {
				leftMotorSpeed = nominalVoltage;
			} else {
				leftMotorSpeed = -nominalVoltage;
			}
		}
		if (rightMotorSpeed > 1.0) {
			rightMotorSpeed = 1.0;
		} else if (rightMotorSpeed < -1.0) {
			rightMotorSpeed = -1.0;
		} else if (Math.abs(rightMotorSpeed) < Constants.NOMINAL_VOLTAGE) {
			if (rightMotorSpeed > 0.0) {
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
				if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) < Math
						.abs(inches * AutoConstants.TICKS_PER_INCH))
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			} else {
				myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}

    public boolean spline(double speed, RobotGrid spline) {
		if (gotStartingENCClicks == false) {
			//gyro.reset();
			gotStartingENCClicks = true;
			System.out.println("Inches " + spline.getDistance());
			startingENCClicksLeft = drive.masterLeft.getEncPosition();
			startingENCClicksRight = drive.masterRight.getEncPosition();
            System.out.println("Start ENC click value = " + startingENCClicksLeft);
		}
		if (spline.getDistance() <= (((drive.masterLeft.getEncPosition() - startingENCClicksLeft)/ AutoConstants.TICKS_PER_INCH + (drive.masterRight.getEncPosition() - startingENCClicksRight)/ AutoConstants.TICKS_PER_INCH))/2) {
			drive.masterLeft.set(0.00);
			drive.masterRight.set(0.00);
			System.out.println("Final NavX Angle: " + gyro.getAngle());
			System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
			return true;
		} else {
			double angleToDrive = (spline.getAngle((((drive.masterLeft.getEncPosition() - startingENCClicksLeft)/ AutoConstants.TICKS_PER_INCH + (drive.masterRight.getEncPosition() - startingENCClicksRight)/ AutoConstants.TICKS_PER_INCH))/2));
			if (spline.getDistance() > 0) {
				if (spline.getDistance() > (((drive.masterLeft.getEncPosition() - startingENCClicksLeft)/ AutoConstants.TICKS_PER_INCH + (drive.masterRight.getEncPosition() - startingENCClicksRight)/ AutoConstants.TICKS_PER_INCH))/2)
						 {
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
				}
			} else {
				myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}
}