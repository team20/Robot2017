package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
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
public class Robot extends IterativeRobot{
	int autoSelected;
	SendableChooser<Number> chooser;
	DriverStation station;
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	DriverVision driverCamera;
	GearMechanism gear;
	FuelTank tank;
	Climber climb;
	DriverControls driver;
	OperatorControls operator;
	AlexDrive alex;
	TsarControls tsar;
	Compressor compressor;
	PIDController turnController;
	AHRS gyro;
	Util util;
	RocketScript getNewScript = new RocketScript();
	String [] rocketScriptData;
	int rocketScriptCurrentCount, rocketScriptLength = 0;
	double rotateToAngleRate, currentRotationRate;
	double angleFromCamera = 0.0, distanceFromCamera = 0.0;
	boolean shooting = false;
	int counter;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {
		station = DriverStation.getInstance();
		flywheel = new FlyWheel();
		collector = new GroundCollector();
		tank = new FuelTank();
		gear = new GearMechanism(flywheel, operator);
		climb = new Climber();
		drive = new DriveTrain();
		driver = new DriverControls(drive, climb);
		//tsar = new TsarControls(drive, climb, tank, gear, flywheel, collector);
		//alex = new AlexDrive(drive,climb);
		operator = new OperatorControls(tank, gear, flywheel, collector);
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		
		drive.shiftHigh();
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);

//		driverCamera = new DriverVision("Driver Camera", 0);
//		driverCamera.startUSBCamera();

		counter = 0;		
		gyro = new AHRS(SerialPort.Port.kMXP);
		util = new Util();
		
		chooser = new SendableChooser<Number>();
		
		//Basic AutoModes
		chooser.addDefault("Do Nothing", 0);
		chooser.addObject("Cross Baseline", 1);
		
		//Starting at the Boiler AutoMode
		chooser.addObject("Start at Boiler and Keep Shooting", 2);
		
		//Just the Gear AutoModes
		chooser.addObject("Middle Gear", 3);
		chooser.addObject("Right Gear", 4);
		chooser.addObject("Left Gear", 5);
		
		//Boiler to Gear AutoModes
		chooser.addObject("Boiler to Closest Gear", 6);
		chooser.addObject("Red: Boiler to Middle Gear", 7);
		chooser.addObject("Blue: Boiler to Middle Gear", 8);

		//Gear to Boiler AutoModes
		chooser.addObject("Red: Middle Gear to Boiler", 9);
		chooser.addObject("Blue: Middle Gear to Boiler", 10);
		chooser.addObject("Red: Right Gear to Boiler", 11);
		chooser.addObject("Blue: Left Gear to Boiler", 12);

		//Hopper to Boiler AutoModes
		chooser.addObject("Red: Hopper to Boiler", 13);
		chooser.addObject("Blue: Hopper to Boiler", 14);

		SmartDashboard.putData("Auto choices ", chooser);
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
		
	}

	/**
	 * This function is called periodically during autonomous
	 */

	@Override
	public void autonomousPeriodic() {
		if(counter == 0){
			Scheduler.getInstance().run();
			autoSelected = (int) chooser.getSelected();
			System.out.println("Auto selected: " + autoSelected);
			switch (autoSelected) {
			case 0:
				System.out.println("Running Do Nothing");
				break;
			case 1:
				System.out.println("Running Cross Baseline");
				rocketScriptData = getNewScript.crossBaseline();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 2:
				System.out.println("Running Stay at Boiler and Shoot");
				rocketScriptData = getNewScript.stayAtBoilerAndShoot();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 3:
				System.out.println("Running Middle Gear");
				rocketScriptData = getNewScript.middleGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 4:
				System.out.println("Running Right Gear");
				rocketScriptData = getNewScript.rightGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 5:
				System.out.println("Running Left Gear");
				rocketScriptData = getNewScript.leftGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 6:
				System.out.println("Running Boiler to Closest Gear");
				rocketScriptData = getNewScript.boilerToClosestSideGear();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 7:
				System.out.println("Running Red Boiler to Middle Gear");
				rocketScriptData = getNewScript.boilerToMiddleGearRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 8:
				System.out.println("Running Blue Boiler to Middle Gear");
				rocketScriptData = getNewScript.boilerToMiddleGearBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 9:
				System.out.println("Running Red Middle Gear to Boiler");
				rocketScriptData = getNewScript.middleGearToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 10:
				System.out.println("Running Blue Middle Gear to Boiler");
				rocketScriptData = getNewScript.middleGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 11:
				System.out.println("Running Red Right Gear to Boiler");
				rocketScriptData = getNewScript.rightGearToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 12:
				System.out.println("Running Blue Left Gear to Boiler");
				rocketScriptData = getNewScript.leftGearToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 13:
				System.out.println("Running Red Hopper to Boiler");
				rocketScriptData = getNewScript.hopperToBoilerRed();
				rocketScriptLength = rocketScriptData.length;
				break;
			case 14:
				System.out.println("Running Blue Hopper to Boiler");
				rocketScriptData = getNewScript.hopperToBoilerBlue();
				rocketScriptLength = rocketScriptData.length;
				break;
			}			
			counter++;
		}
		if (rocketScriptCurrentCount < rocketScriptLength) {
			// System.out.println("********running rocketScript counter");
			String[] values = rocketScriptData[rocketScriptCurrentCount].split(";");
			System.out.println("Value 1: " + values[0] + " Value 2: " + values[1]);

			if (Integer.parseInt(values[0]) == RobotModes.VISION_TARGET) {
				String getSocketData;
				gyro.reset();
				Timer.delay(0.5);
				getSocketData = util.getCameraAngle();
				String[] socketValues = getSocketData.split("\\*");
				distanceFromCamera = Double.parseDouble(socketValues[0]);
				angleFromCamera = Float.parseFloat(socketValues[1]);
				System.out.println("Distance from camera: " + distanceFromCamera);
				System.out.println("Angle from camera: " + angleFromCamera);
				if (rocketTurn(angleFromCamera)) {
					drive.masterLeft.setEncPosition(0);
					if (driveStraight(0.65, distanceFromCamera, angleFromCamera)) {
						rocketScriptCurrentCount++;
					}					
				}
			}

			if (Integer.parseInt(values[0]) == RobotModes.ROCKET_TURN) {
				if (rocketTurn(Double.parseDouble(values[1]))) {
					rocketScriptCurrentCount++;
				}
			}
			if (Integer.parseInt(values[0]) == RobotModes.DRIVE_DISTANCE) {
				if (driveStraight(1.0, Double.parseDouble(values[1]), 6.5)) {
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
			if(Integer.parseInt(values[0]) == RobotModes.ARC_TURN_RIGHT){
				if(arcTurn(Double.parseDouble(values[1]), Double.parseDouble(values[2]), true)){
					rocketScriptCurrentCount++;
				}
			}
			if(Integer.parseInt(values[0]) == RobotModes.ARC_TURN_LEFT){
				if(arcTurn(Double.parseDouble(values[1]), Double.parseDouble(values[2]), false)){
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
		drive.masterRight.setVoltageRampRate(60);
		drive.masterLeft.setVoltageRampRate(60);
		gear.automated = true;
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
		
	}
	
	public boolean rocketTurn(double angle){
		return true;
	}
	
	public boolean driveStraight(double speed, double inches, double multiplier){
		if(drive.leftEncoder()){
			if(Math.abs(drive.masterLeft.getEncPosition()) > Math.abs(inches*AutoConstants.TICKS_PER_INCH)){
				drive.stopDrive();
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
		}else if(drive.rightEncoder()){
			if(Math.abs(drive.masterRight.getEncPosition()) > Math.abs(inches*AutoConstants.TICKS_PER_INCH)){
				drive.stopDrive();
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
		}else{
			drive.stopDrive();
			return false;
		}
	}
    public boolean arcTurn(double middleToLongSide, double middleToShortSide, boolean direction) {//half of the long side of an elipse b is half the short side and c is the robot with
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
    }
}