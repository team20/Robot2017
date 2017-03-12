package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

public class DriveTrain {
	DriverStation d = DriverStation.getInstance();
	DoubleSolenoid shifter = new DoubleSolenoid(Constants.DRIVETRAIN_EXTEND_PORT,
			Constants.DRIVERTRAIN_RETRACT_PORT);
	CANTalon masterRight, followerRightOne, followerRightTwo;
	CANTalon masterLeft, followerLeftOne, followerLeftTwo;
	int state;
	double turnAngle = 0.0, startingAngle = 0.0;
	double adjustment, rotateToAngleRate, currentRotationRate;
	double multiplier;
	boolean kArcadeStandard_Reported = false;
	boolean setSetpoint;
	boolean highGear;
	
	public DriveTrain() {
		//Setting the Motor Port Numbers
		masterRight = new CANTalon(Constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		masterRight.reverseOutput(false);
		followerRightOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE);
		followerRightTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO);
		masterLeft = new CANTalon(Constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		masterLeft.reverseOutput(false);
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
	}
	public void resetTalons(){
		masterLeft.reset();
		masterRight.reset();
	}		
	public void drive(double speed, double rightTurn, double leftTurn) { // drives
		if(shifter.get() == DoubleSolenoid.Value.kReverse){
			rightTurn*=.95;
			leftTurn*=.95;
			if (speed != 0) { //forward
				adjustment = 0;
			}
			masterRight.set(speed - rightTurn + leftTurn + adjustment);
			masterLeft.set(-speed + leftTurn - rightTurn);
		}else{
			if (speed != 0) { //forward
				adjustment = 0;
			}
			masterRight.set(speed - rightTurn + leftTurn + adjustment);
			masterLeft.set(-speed + leftTurn - rightTurn);
		}
	}
	
	public boolean leftEncoder(){
		boolean leftEncoder = false;
		try{
		FeedbackDeviceStatus sensorStatusLeft = masterLeft.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
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
			FeedbackDeviceStatus sensorStatusRight = masterRight.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
			rightEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusRight);
		}catch(Exception e){
			System.out.println("Right Encoder Error: " + e.toString());
		}
		return rightEncoder;
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
		highGear = true;
		shifter.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftLow() { // shifts into low gear ratio
		shifter.set(DoubleSolenoid.Value.kForward);
		highGear = false;
	}
	
	public boolean turnAngle(double angle){
		return false;
	}
}  