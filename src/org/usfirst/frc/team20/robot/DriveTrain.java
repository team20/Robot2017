package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SerialPort;

public class DriveTrain {
	DriverStation d = DriverStation.getInstance();
	DoubleSolenoid shifter = new DoubleSolenoid(Constants.DRIVETRAIN_EXTEND_PORT,
			Constants.DRIVERTRAIN_RETRACT_PORT);
	CANTalon masterRight, followerRightOne, followerRightTwo;
	CANTalon masterLeft, followerLeftOne, followerLeftTwo;
	PIDController turnController;
	AHRS gyro;
	int state;
	double turnAngle = 0.0, startingAngle = 0.0;
	double adjustment, rotateToAngleRate, currentRotationRate;
	double multiplier;
	boolean kArcadeStandard_Reported = false;
	boolean setSetpoint;
	
	public DriveTrain(VisionTargeting v) {
		//Setting the Motor Port Numbers
		masterRight = new CANTalon(Constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		followerRightOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE);
		followerRightTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO);
		masterLeft = new CANTalon(Constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		followerLeftOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE);
		followerLeftTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO);
		//Setting the Follower Motors to Their Respective Masters
		followerRightOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightOne.set(masterRight.getDeviceID());
		followerRightOne.reverseOutput(true);
		followerRightTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightTwo.set(masterRight.getDeviceID());
		followerRightTwo.reverseOutput(true);
		followerLeftOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftOne.set(masterLeft.getDeviceID());
		followerLeftOne.reverseOutput(true);
		followerLeftTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftTwo.set(masterLeft.getDeviceID());
		followerLeftTwo.reverseOutput(true);
		//Setting the Encoders on the Master Motors
		masterRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterLeft.setEncPosition(0);
		masterRight.setEncPosition(0);
		multiplier = 6.5;
		gyro = new AHRS(SerialPort.Port.kMXP);
		gyro.reset();
		System.out.println("Initial NavX Angle" + gyro.getYaw());
//		turnController = new PIDController(Constants.NavX_P, Constants.NavX_I, Constants.NavX_D,
//				Constants.NavX_F, gyro, this);
//		turnController.setInputRange(-180.0f, 180.0f);
//		turnController.setOutputRange(-1.0, 1.0);
//		turnController.setAbsoluteTolerance(Constants.NavX_Tolerance_Degrees);
//		turnController.setContinuous(false);
//		setSetpoint = false;
//		turnController.enable();
//		Timer.delay(0.5);
	}
	
	public double testNavx(){
		return gyro.getRoll();
	}
	
	public void setTurnController(){
		turnController.setSetpoint(turnAngle);
		turnController.enable();
	}
	
	public void drive(double speed, double rightTurn, double leftTurn) { // drives
		if (speed != 0) { //forward
//			gyro.reset();
//			startingAngle = gyro.getRoll();
			adjustment = 0;
		}
//		if (gyro.getYaw() > 0){
//			adjustment = (speed * 0.1); // 0.125 can be changed so that it is > 0.1 and < 0.14
//		}else if (gyro.getYaw() < 0){
//			adjustment = -(speed * 0.1);// 0.125 can be changed so that it is > 0.1 and < 0.14
//		}else{
//			adjustment = 0;
//		}
//		if(speed < 0){
//			adjustment = -adjustment;
//		}
		masterRight.set(speed - rightTurn + leftTurn + adjustment);
		masterLeft.set(-speed + leftTurn - rightTurn);
	}
	
	public boolean leftEncoder(){
		boolean leftEncoder = false;
		try{
//		FeedbackDeviceStatus sensorStatusLeft = masterLeft.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		FeedbackDeviceStatus sensorStatusLeft = masterLeft.isSensorPresent(FeedbackDevice.QuadEncoder);
		leftEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusLeft);
		}catch(Exception e){
			System.out.println("Left Encoder Error: " + e.toString());
			rightEncoder();
		}
		return leftEncoder;
	}

	public boolean rightEncoder(){
		boolean rightEncoder = false;
		try{
//			FeedbackDeviceStatus sensorStatusRight = masterRight.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
			FeedbackDeviceStatus sensorStatusRight = masterRight.isSensorPresent(FeedbackDevice.QuadEncoder);
			rightEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusRight);
		}catch(Exception e){
			System.out.println("Right Encoder Error: " + e.toString());
		}
		return rightEncoder;
	}
	public boolean driveDistanceStraightLeftEncoder(double speed, double inches){
		boolean doneDriving = false;
		currentRotationRate = rotateToAngleRate;
		System.out.println("CurrentRR: " + currentRotationRate);
		if(masterLeft.getEncPosition()/4096*Math.PI*4 > (inches*multiplier)){
			System.out.println("******************* Done Driving");
			turnDrive(0.0, 0.0);
			doneDriving = true;
		}else{
			System.out.println("***** Inches " + masterLeft.getEncPosition()/1024*Math.PI*4);			
			turnDrive(speed, currentRotationRate);
			doneDriving = false;
		}
		System.out.println("Done Driving? " + doneDriving);
		return doneDriving;
	}	
		
	public boolean driveDistanceStraightRightEncoder(double speed, double inches) {
		boolean doneDriving = false;
		currentRotationRate = rotateToAngleRate;
		System.out.println("CurrentRR: " + currentRotationRate);
		if(masterRight.getEncPosition()/4096*Math.PI*4 > (inches*multiplier)){
			System.out.println("******************* Done Driving");
			turnDrive(0.0, 0.0);
			doneDriving = true;
		}else{
			System.out.println("***** Inches " + masterRight.getEncPosition()/1024*Math.PI*4);			
			turnDrive(speed, currentRotationRate);
			doneDriving = false;
		}
		System.out.println("Done Driving? " + doneDriving);
		return doneDriving;
	}	
	
	public void turnRight(double speed) { // turns right
		masterRight.set(-speed);
		masterLeft.set(-speed);
	}
	
	public void turnLeft(double speed) { // turns left
		masterRight.set(speed);
		masterLeft.set(speed);
	}
	
	public void stopDrive(){
		masterRight.set(0);
		masterLeft.set(0);
	}

	public void shiftHigh() { // shifts into high gear ratio
		shifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftLow() { // shifts into low gear ratio
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void turnDrive(double moveValue, double rotateValue) {
		double leftMotorSpeed;
		double rightMotorSpeed;
		if (moveValue >= 0.0) {
			moveValue = moveValue * moveValue;
		} else {
			moveValue = -(moveValue * moveValue);
		}
		if (rotateValue >= 0.0) {
			rotateValue = rotateValue * rotateValue;
		} else {
			rotateValue = -(rotateValue * rotateValue);
		}
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		masterRight.set(rightMotorSpeed);
		masterLeft.set(-leftMotorSpeed*0.95);
	}
	public boolean turnAngle(double angle){
//		System.out.println("Set Angle " + angle);
//		System.out.println("Turn Controller Setpoint " + turnController.getSetpoint());
		turnAngle = angle;
		if(!setSetpoint) {
			setTurnController();
			setSetpoint = true;
		}
		boolean doneTurning = false;
		currentRotationRate = rotateToAngleRate;
		if (Math.abs(angle - gyro.getYaw()) < .6 && Math.abs(currentRotationRate) < .3) {
			System.out.println("**********************Stopped Turning");
			currentRotationRate = 0;
			turnDrive(0.0, 0);
			doneTurning = true;
		} else {
			try {
//				System.out.println("**********************Turning");
				turnDrive(0.0, currentRotationRate);
				System.out.println("Current Rotation Rate: " + currentRotationRate);
				System.out.println("Navx " + gyro.getAngle());
//				System.out.println(" currentRotationRate  = " + currentRotationRate);
//				System.out.println(" Math.abs(angle - gyro.getAngle()) = " + Math.abs(angle - gyro.getYaw()));
			} catch (RuntimeException ex) {
				DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
			}
		}
		return doneTurning;
	}
}  