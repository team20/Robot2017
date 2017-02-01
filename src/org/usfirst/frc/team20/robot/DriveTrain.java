package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort;

public class DriveTrain implements PIDOutput {
	DriverStation d = DriverStation.getInstance();
	DoubleSolenoid shifter = new DoubleSolenoid(Constants.DRIVETRAIN_EXTEND_PORT, Constants.DRIVERTRAIN_RETRACT_PORT);
	CANTalon masterRight;
	CANTalon followerRightOne;
	CANTalon followerRightTwo;
	CANTalon masterLeft;
	CANTalon followerLeftOne;
	CANTalon followerLeftTwo;
	AHRS gyro = new AHRS(SerialPort.Port.kMXP);
	PIDController turnController;
	double startingAngle = 0, adjustment;
	double rotateToAngleRate;
	static final double kP = 0.02;
	static final double kI = 0.0025;
	static final double kD = 0.00;
	static final double kF = 0.00;
	static final double kToleranceDegrees = 2.0f;

	public DriveTrain() {
		masterRight = new CANTalon(Constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		followerRightOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE);
		followerRightTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO);
		masterLeft = new CANTalon(Constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		followerLeftOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE);
		followerLeftOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO);
		followerRightOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightOne.set(masterRight.getDeviceID());
		followerRightTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightTwo.set(masterRight.getDeviceID());
		followerLeftOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftOne.set(masterLeft.getDeviceID());
		followerLeftTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftTwo.set(masterLeft.getDeviceID());
		turnController = new PIDController(kP, kI, kD, kF, gyro, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
//		masterRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		masterLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		masterLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	}

	public void drive(double speed, double rightTurn, double leftTurn) { // drives
		if (speed < 0.1 && speed > -0.1) { //forward
			gyro.reset();
			startingAngle = gyro.getYaw();
			adjustment = 0;
		}

		if (gyro.getYaw() > 0.1)
			adjustment = (speed * 0.125); // 0.125 can be changed so that it is
											// > 0.1 and < 0.14
		else if (gyro.getYaw() < -0.1)
			adjustment = -(speed * 0.125);// 0.125 can be changed so that it is
											// > 0.1 and < 0.14
		else
			adjustment = 0;

		masterRight.set(speed - rightTurn + leftTurn - adjustment);
		masterLeft.set(-speed + leftTurn - rightTurn);
	}

	// Copied from wpilib arcadeDrive
	public void arcadeDrive(double moveValue, double rotateValue) {
		double leftMotorSpeed;
		double rightMotorSpeed;

		// moveValue = limit(moveValue);
		// rotateValue = limit(rotateValue);

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

		masterLeft.set(leftMotorSpeed);
		masterRight.set(rightMotorSpeed);
	}
	
//	public boolean rightEncoder(){
////		FeedbackDeviceStatus sensorStatusRight = masterRight.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
//		FeedbackDeviceStatus sensorStatusRight = masterRight.isSensorPresent(FeedbackDevice.QuadEncoder);
//		boolean rightEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusRight);
//		return rightEncoder;
//	}
	
	public boolean leftEncoder(){
//		FeedbackDeviceStatus sensorStatusLeft = masterLeft.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		FeedbackDeviceStatus sensorStatusLeft = masterLeft.isSensorPresent(FeedbackDevice.QuadEncoder);
		boolean leftEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusLeft);
		return leftEncoder;
	}
	
	public void driveDistanceStraight(double speed, double inches) {
		
	}
	
	public void turnRight(double speed) { // turns right
		masterRight.set(-speed);
		masterLeft.set(-speed);
	}
	
	public void turnLeft(double speed) { // turns left
		masterRight.set(speed);
		masterLeft.set(speed);
	}

	public void shiftHigh() { // shifts into high gear ratio
		shifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftLow() { // shifts into low gear ratio
		shifter.set(DoubleSolenoid.Value.kForward);
	}

	public void turnAngle(double angle) {
		turnController.setSetpoint(angle);
		turnController.enable();
		double currentRotationRate;
		currentRotationRate = rotateToAngleRate;
		try {
			arcadeDrive(0.0, currentRotationRate);
			// System.out.println(hrs.getAngle());
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
		}
	}

	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}