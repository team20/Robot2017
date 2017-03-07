package org.usfirst.frc.team20.robot;

import java.io.IOException;
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

/**dum
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot  {
	String autoSelected;
	SendableChooser<String> chooser;
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
	//PIDController turnController;
	//navx needs to be declared as globla
	//do not put this in robot init
	AHRS gyro = new AHRS(SerialPort.Port.kMXP);
	
	Util util;
	TsarControls tsar;
	RocketScript getNewScript = new RocketScript();
	String[] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	boolean shooting = false;
	//double turnCameraAngle = 0.0;
	boolean runAutoInit = false;
	boolean checkButton = false;
    int startingENCClicks;
    boolean gotStartingENCClicks = false;
    int autoModeSubStep = 0;
	

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
		chooser = new SendableChooser<String>();

		// Basic AutoModes
		chooser.addDefault("Do Nothing", "DoNothing");
		chooser.addObject("Cross Baseline", "CrossBaseline");

		// Starting at the Boiler AutoMode
		chooser.addObject("Start at Boiler and Keep Shooting", "StayAtBoilerAndShoot");

		// Just the Gear AutoModes
		chooser.addObject("Middle Gear", "MiddleGear");
		chooser.addObject("Right Gear", "RightGear");
		chooser.addObject("Left Gear", "LeftGear");

		// Boiler to Gear AutoModes
		chooser.addObject("Boiler to Closest Gear", "BoilerToClosestSideGear");
		chooser.addObject("Red: Boiler to Middle Gear", "BoilerToMiddleGearRed");
		chooser.addObject("Blue: Boiler to Middle Gear", "BoilerToMiddleGearBlue");

		// Gear to Boiler AutoModes
		chooser.addObject("Red: Middle Gear to Boiler", "MiddleGearToBoilerRed");
		chooser.addObject("Blue: Middle Gear to Boiler", "MiddleGearToBoilerBlue");
		chooser.addObject("Red: Right Gear to Boiler", "RightGearToBoilerRed");
		chooser.addObject("Blue: Left Gear to Boiler", "LeftGearToBoilerBlue");

		// Hopper to Boiler AutoModes
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
		drive.masterLeft.setVoltageRampRate(60);
		drive.masterRight.setVoltageRampRate(60);
		gyro.reset();
		Timer.delay(0.5);
		drive.masterLeft.setEncPosition(0);
		drive.masterLeft.enable();
		autoModeSubStep = 0;
		//autoSelected = (String) chooser.getSelected();
		//autoSelected = SmartDashboard.getString(autoSelected, "Do Nothing");
		Scheduler.getInstance().run();
		autoSelected = "testCode";
		System.out.println("Auto selected: " + autoSelected);
		rocketScriptData = getNewScript.leftGear();
		rocketScriptCurrentCount = 0;
		rocketScriptLength = rocketScriptData.length;
	
	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {

	   SmartDashboard.putString("DB/String 3", "Gyro angle: " + gyro.getAngle());
	   SmartDashboard.putString("DB/String 4","Encoder: " + drive.masterLeft.getEncPosition());
		if (rocketScriptCurrentCount < rocketScriptLength) {
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
		
			if (Integer.parseInt(values[0]) == RobotModes.SMART_DRIVE_STRAIGHT) {
				if (driveStraight(0.60, (distanceFromCamera * AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) - 20,
						angleFromCamera)) {//turnCameraAngle)) {
					rocketScriptCurrentCount++;
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.SMART_TURN_ANGLE) {
				if (turnAngle(angleFromCamera)) {
					drive.masterLeft.setEncPosition(0);
					rocketScriptCurrentCount++;
				}
			} 
			if (Integer.parseInt(values[0]) == RobotModes.ROCKET_TURN) {
				if (rocketTurn(Double.parseDouble(values[1]))){
					rocketScriptCurrentCount++;
				}
				
			}

			if (Integer.parseInt(values[0]) == RobotModes.GET_CAMERA_ANGLE) {
				//System.out.println("Turn camera ");
				String getSocketData;
				if (autoModeSubStep == 0)
				{
					Timer.delay(1.0);
					getSocketData = util.getCameraAngle();
					String [] socketValues = getSocketData.split("\\*");	
					//Timer.delay(1.0);
					distanceFromCamera = Double.parseDouble(socketValues[0]);
					angleFromCamera = Double.parseDouble(socketValues[1]);
					autoModeSubStep = 1;
					gyro.reset();
					Timer.delay(0.5);
				} else if (autoModeSubStep == 1)
				{
					System.out.println("angleFromCamera    " + angleFromCamera);
					System.out.println("************" + gyro.getAngle());
					if (rocketTurn(angleFromCamera))
					{
							System.out.println("************" + gyro.getAngle());
							autoModeSubStep = 2;
							gyro.reset();
							Timer.delay(0.5);
					}					
				} else if (autoModeSubStep == 2)
				{
					//System.out.println("distanceFromCamera    " + distanceFromCamera);
					if (dumbDriveStraight(0.5, distanceFromCamera)) {
						gotStartingENCClicks = false;
						autoModeSubStep = 0;
						rocketScriptCurrentCount++;
					}
				}
				
			//	turnController.reset();
//				turnController.setSetpoint(angleFromCamera);
//				turnController.enable();
			}

			if (Integer.parseInt(values[0]) == RobotModes.RAW_TURN_ANGLE) {

				if (rocketTurn(Double.parseDouble(values[1]))) {
					rocketScriptCurrentCount++;
					drive.masterLeft.setEncPosition(0);
				}
 			}  
			if (Integer.parseInt(values[0]) == RobotModes.RAW_DRIVE_STRAIGHT) {
				if (dumbDriveStraight(0.5, Double.parseDouble(values[1]))) {
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
			if (Integer.parseInt(values[0]) == RobotModes.WAIT_FOR_GEAR) {	//TODO add a counter so it doesn't drive immedately after it is lifted
				if (gear.checkGear() == false) {
					rocketScriptCurrentCount++;
				}
			}
		}
	}

	@Override
	public void teleopInit() {
		drive.masterLeft.setVoltageRampRate(60);//60
		drive.masterRight.setVoltageRampRate(60); //60
		flywheel.stopFlywheel();
		collector.stopCollector();
		operator.shooting = false;
		tank.stopTank();
		flywheel.flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
	}

	/**
	 * This function is called periodically during operator control
	 */

	@Override
	public void teleopPeriodic() {
		//driver.driverControls();
		//operator.operatorControls();
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
		//   System.out.println("enc = " + drive.masterRight.getEncPosition());
	}

	private boolean turnAngle(double cameraAngle) {
		boolean doneTurning = false;
		currentRotationRate = rotateToAngleRate;
		if (Math.abs(cameraAngle - gyro.getYaw()) < 2.0) {
			currentRotationRate = 0.0;
			myDrive.arcadeDrive(0.0, 0.0);
			doneTurning = true;
		} else {
			try {
				rocketTurn(cameraAngle);
			} catch (RuntimeException ex) {
				DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
			}
		}
		return doneTurning;
	}

	public boolean turnRoughAngle(double turnAngle) {
		if (turnAngle < 0) {
			if (Math.abs(gyro.getYaw() - turnAngle) < 5) {
				myDrive.arcadeDrive(0, 0);
				return true;
			} else {
				myDrive.arcadeDrive(0, -0.6);
			}
		} else {
			if (Math.abs(gyro.getYaw() - turnAngle) < 5) {
				myDrive.arcadeDrive(0, 0);
				return true;
			} else {
				myDrive.arcadeDrive(0, .6);
			}
		}
		return false;
	}

	public boolean dumbDriveStraight(double speed, double inches) {
		if (drive.leftEncoder()) {
			//if (Math.abs((double) drive.masterLeft.getEncPosition() / 4096.0 * Math.PI * 4) > Math.abs(inches)
			//		* AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) {
			if (gotStartingENCClicks == false) {
				gotStartingENCClicks = true;
				startingENCClicks =  drive.masterLeft.getEncPosition();
				System.out.println("Start ENC click value = " +  startingENCClicks);
			}
			if (Math.abs((double)( drive.masterLeft.getEncPosition() - startingENCClicks)) > Math.abs(inches * 667.00 )){
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);
				//myDrive.arcadeDrive(0, 0);
				System.out.println("Enc value after speed 0 " + drive.masterLeft.getEncPosition());
				return true;
			} else {
				if (inches > 0) {
					myDrive.arcadeDrive(speed, 0);
					//myDrive.arcadeDrive(speed,0);
				} else {
					//myDrive.arcadeDrive(-speed,0);
				} 	
			}
			return false;
		} else if (drive.rightEncoder()) {	//TODO make ticks a constant, Jesus. Why. 
			if (Math.abs((double) (drive.masterRight.getEncPosition() -  startingENCClicks)) > Math.abs(inches * 667.00 )){
			//if (Math.abs(drive.masterRight.getEncPosition() / 4096 * Math.PI * 4) > Math.abs(inches)
				//	* AutoConstants.DRIVE_STRAIGHT_MULTIPLIER) {
				drive.masterLeft.set(0.00);
				drive.masterRight.set(0.00);
				
				System.out.println("Done driving. Enc = " + drive.masterLeft.getEncPosition());
				//myDrive.arcadeDrive(0, 0);
				return true;
			} else {
				if (inches > 0) {
					drive.masterRight.set(speed);
					drive.masterLeft.set(-speed);
				} else {
					drive.masterRight.set(-speed);
					drive.masterLeft.set(speed);
				}
			}
		}
		return true;
	}

	public boolean rocketTurn(double angleToTurn) {
		boolean weAreDone = false;
		if (angleToTurn > 1) {
			System.out.println("Positive angle");
			if ((angleToTurn - gyro.getAngle()) < 1.00) {
				weAreDone = true;
				myDrive.arcadeDrive(0, 0);
				System.out.println(
						"*******************************navx = " + gyro.getAngle() + "\n Angle to turn " + angleToTurn);

			} else {
				drive.masterLeft.set(-0.40);
				drive.masterRight.set(-0.40);
				
	//			myDrive.arcadeDrive(0, .55);
			}
		}

		if (angleToTurn < 1) {
			if ((angleToTurn - gyro.getAngle()) > -1.00) {
				weAreDone = true;
				myDrive.arcadeDrive(0, 0);
				System.out.println(
						"*******************************navx = " + gyro.getAngle() + "\n Angle to turn " + angleToTurn);
			} else {
				//myDrive.arcadeDrive(0, -.55);
				drive.masterLeft.set(0.40);
				drive.masterRight.set(0.40);
		
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
