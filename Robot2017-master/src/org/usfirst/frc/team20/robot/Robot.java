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
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
	AlexDrive alex;
	TsarControls tsar;
	Compressor compressor;
	DriverVision cam;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP); // DO NOT PUT IN ROBOT INIT
	Util util;
	RocketScript getNewScript = new RocketScript();
	String[] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	boolean shooting = false;
	boolean runAutoInit = false;
	boolean checkButton = false;
	int startingENCClicks;
	boolean gotStartingENCClicks = false;
	int autoModeSubStep = 0;
	boolean resetGyro = false;
	boolean selectAutoMode = false;
	boolean setStartTime = false;
	boolean setStartTimeFlywheel = false;
	double startTime;

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
		//alex = new AlexDrive(drive, climb);
		//tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);

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

	public void autonomousInit() {
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		gyro.reset();
		drive.masterLeft.setEncPosition(0);
		drive.masterLeft.enable();
		autoModeSubStep = 0;
		rocketScriptCurrentCount = 0;
		gear.gearFlapOut();
		boolean middleGear = SmartDashboard.getBoolean("DB/Button 0", false);
		boolean shootBoiler = SmartDashboard.getBoolean("DB/Button 1", false);
		boolean slamRed = SmartDashboard.getBoolean("DB/Button 2", false);
		boolean slamBlue = SmartDashboard.getBoolean("DB/Button 3", false);
		selectAutoMode = true;
		// // CHANGE BELOW HERE
		// rocketScriptData = getNewScript.middleGearTimed(); //TODO this is the line you change
		// rocketScriptLength = rocketScriptData.length;
		if(middleGear == true){
			rocketScriptData = getNewScript.middleGearTimed();
			rocketScriptLength = rocketScriptData.length;
		}else if(shootBoiler == true){
			rocketScriptData = getNewScript.stayAtBoilerAndShoot();
			rocketScriptLength = rocketScriptData.length;
		}else if(slamRed == true){
			rocketScriptData = getNewScript.hopperToBoilerRed();
			rocketScriptLength = rocketScriptData.length;
		}else if(slamBlue == true){
			rocketScriptData = getNewScript.hopperToBoilerBlue();
			rocketScriptLength = rocketScriptData.length;
		}else{
			
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
					resetGyro = false;
					rocketScriptCurrentCount++;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.GO_CAMERA_DISTANCE) {
				if (cameraDriveStraight()){
					resetGyro = false;
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.FAST_DRIVE_STRAIGHT) {
				if (fastDriveStraight(Double.parseDouble(values[1]), Double.parseDouble(values[2]) - AutoConstants.STOPPING_INCHES,
						Double.parseDouble(values[3]))) {
					System.out.println("In the Drive Straight Rocket Script");
					gotStartingENCClicks = false;
					gyro.reset();
					rocketScriptCurrentCount++;
				}
				System.out.println("Driving Straight");
			}
			if (Integer.parseInt(values[0]) == RobotModes.SHOOTING) {
				flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
				System.out.println("Flywheel RPMS " + flywheel.flywheelSpeed());
				if (flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)) {
					collector.intake(1);
					tank.tankMotorIntoFlywheel(0.4);
					shooting = true;
				}
				if (shooting) {
					tank.runAgitator();
					if (!setStartTimeFlywheel){
						startTime = Timer.getFPGATimestamp();
						setStartTimeFlywheel = true;
					}
					if (Timer.getFPGATimestamp() - startTime < Double.parseDouble(values[1])) {
						System.out.println("******************************DONE SHOOTING");
						rocketScriptCurrentCount++;						
					}
				}

			}
			if (Integer.parseInt(values[0]) == RobotModes.STOP_SHOOTING) {
				flywheel.stopFlywheel();
				collector.stopCollector();
				tank.stopTank();
				shooting = false;
				setStartTimeFlywheel = false;
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
			if(Integer.parseInt(values[0]) == RobotModes.TIME_DRIVE){
				if(fastDriveStraightTimer(Double.parseDouble(values[1]), Double.parseDouble(values[2]), true)){
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.LOW_GEAR){
				drive.shiftLow();
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
		gear.automated = false;
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		driver.driverControls();
		operator.operatorControls();
		//tsar.tsarControls();
		//alex.AlexControls();
		SmartDashboard.putString("DB/String 0", "Flywheel Speed");
		SmartDashboard.putString("DB/String 1", "Flywheel Ready?");
		SmartDashboard.putString("DB/String 2", "High Gear?");
		SmartDashboard.putString("DB/String 3", "Collector Jam?");
		SmartDashboard.putString("DB/String 4", Double.toString(flywheel.flywheelSpeed()));
		SmartDashboard.putString("DB/String 5", Boolean.toString(flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)));
		SmartDashboard.putString("DB/String 6", Boolean.toString(drive.highGear));
		SmartDashboard.putString("DB/String 7", Boolean.toString(collector.collectorJam()));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println("Left: " + drive.masterLeft.getEncPosition());
		System.out.println("									Right: " + drive.masterRight.getEncPosition());
	}

	//Auto Methods
	public boolean turnWithCamera() {
		return fastDriveStraight(AutoConstants.CAMERA_TURN_SPEED, 5.0, angleFromCamera);
	}
	public boolean cameraDriveStraight() {
		return fastDriveStraight(AutoConstants.CAMERA_DRIVE_SPEED, distanceFromCamera, angleFromCamera);
	}

	public boolean fastDriveStraightTimer(double speed, double howMuchTime, boolean withGyro) {
		boolean done = false;
		if (!setStartTime) {
			startTime = Timer.getFPGATimestamp();
			setStartTime = true;
			gyro.reset();
		}
		System.out.println("Time is: " + (Timer.getFPGATimestamp() - startTime));
		if (Timer.getFPGATimestamp() - startTime < howMuchTime) {
			if (Math.abs(speed) > 0.00) {
				if (withGyro)
					myDrive.arcadeDrive(speed, -(gyro.getAngle() * 0.070));
				else // no Gyro
					myDrive.arcadeDrive(speed, 0);
			}
		} else {
			if (withGyro)
				myDrive.arcadeDrive(0.00, -(gyro.getAngle() * .070));
			else
				myDrive.arcadeDrive(0.00, 0);
			done = true;
			setStartTime = false;
		}
		return done;
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
				if (Math.abs((double) (drive.masterLeft.getEncPosition() - startingENCClicks)) > Math
						.abs(inches * AutoConstants.TICKS_PER_INCH))
					System.out.println("*88888888888888888888888888888I am driving");
					myDrive.arcadeDrive(speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			} else {
				myDrive.arcadeDrive(-speed, -((gyro.getAngle() - angleToDrive) * Constants.DRIVING_P)); // .07
			}
		}
		return false;
	}

/*	public boolean arcTurn(double middleToLongSide, double middleToShortSide, boolean direction) {//half of the long side of an elipse b is half the short side and c is the robot with
        double leftArc;
        double rightArc;
        double width = 32.0;
    	width /= 2;
        double smallA = middleToLongSide - width;
        double smallB = middleToShortSide - width;
        double largeA = middleToLongSide + width;
        double largeB = middleToShortSide + width;
        if (direction) {
            rightArc = Math.PI*Math.abs(3*(smallA + smallB)-Math.sqrt((3*smallA+smallB)*(middleToLongSide+smallB)))/4;
            leftArc = Math.PI*Math.abs(3*(largeA + largeB)-Math.sqrt((3*largeA+largeB)*(largeA+3*largeB)))/4;
            if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH){
            	drive.stopDrive();
            	return true;
            }else if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() < leftArc*AutoConstants.TICKS_PER_INCH){
            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
            	drive.masterRight.set(0);
            	return false;
            }else if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() < rightArc*AutoConstants.TICKS_PER_INCH){
            	drive.masterLeft.set(0);
            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
            	return false;
            }else{
            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
            	return false;
            }
        }else{
            rightArc = Math.PI*Math.abs(3*(largeA + largeB)-Math.sqrt((3*largeA+largeB)*(largeA+3*largeB)))/4;
            leftArc = Math.PI*Math.abs(3*(smallA + smallB)-Math.sqrt((3*smallA+smallB)*(middleToLongSide+smallB)))/4;
            if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH){
            	drive.stopDrive();
            	return true;
            }else if(drive.masterRight.getEncPosition() > rightArc*AutoConstants.TICKS_PER_INCH && drive.masterLeft.getEncPosition() < leftArc*AutoConstants.TICKS_PER_INCH){
            	drive.masterRight.set(0);
            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
            	return false;
            }else if(drive.masterLeft.getEncPosition() > leftArc*AutoConstants.TICKS_PER_INCH && drive.masterRight.getEncPosition() < rightArc*AutoConstants.TICKS_PER_INCH){
            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
            	drive.masterLeft.set(0);
            	return false;
            }else{
            	drive.masterRight.set(AutoConstants.ARC_SPEED*(rightArc/leftArc));
            	drive.masterLeft.set(AutoConstants.ARC_SPEED);
            	return false;
            }
        }
    } */
}
