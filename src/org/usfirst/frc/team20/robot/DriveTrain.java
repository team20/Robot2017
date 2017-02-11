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
	DoubleSolenoid shifter = new DoubleSolenoid(Constants.DRIVETRAIN_EXTEND_PORT,
			Constants.DRIVERTRAIN_RETRACT_PORT);
	CANTalon masterRight;
	CANTalon followerRightOne;
	CANTalon followerRightTwo;
	CANTalon masterLeft;
	CANTalon followerLeftOne;
	CANTalon followerLeftTwo;
	AHRS gyro;
	PIDController turnController;
	int state;
	double turnAngle = 0.0;
	double startingAngle = 0, adjustment;
	double rotateToAngleRate;
	double currentRotationRate;
	double multiplier;
	boolean kArcadeStandard_Reported = false;
	
	public DriveTrain(VisionTargeting v) {
		masterRight = new CANTalon(Constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		followerRightOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE);
		followerRightTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO);
		masterLeft = new CANTalon(Constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		followerLeftOne = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE);
		followerLeftTwo = new CANTalon(Constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO);
		followerRightOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightOne.set(masterRight.getDeviceID());
		followerRightTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerRightTwo.set(masterRight.getDeviceID());
		followerLeftOne.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftOne.set(masterLeft.getDeviceID());
		followerLeftTwo.changeControlMode(CANTalon.TalonControlMode.Follower);
		followerLeftTwo.set(masterLeft.getDeviceID());
//		masterRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		masterLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		masterLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		multiplier = 6.5;
	}
	
	public void initializeNavx(){
			gyro = new AHRS(SerialPort.Port.kMXP);
	}
	
	public void setTurnController(){
		turnController = new PIDController(Constants.NavX_P, Constants.NavX_I, Constants.NavX_D, Constants.NavX_F, gyro,
				this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(Constants.NavX_Tolerance_Degrees);
		turnController.setContinuous(true);
		turnController.setSetpoint(turnAngle);
		turnController.enable();
	}
	
	public void drive(double speed, double rightTurn, double leftTurn) { // drives
		if (speed < 0.1 && speed > -0.1) { //forward
			gyro.reset();
			startingAngle = gyro.getYaw();
			adjustment = 0;
		}
		if (gyro.getYaw() > 0.1)
			adjustment = (speed * 0.1); // 0.125 can be changed so that it is
											// > 0.1 and < 0.14
		else if (gyro.getYaw() < -0.1)
			adjustment = -(speed * 0.1);// 0.125 can be changed so that it is
											// > 0.1 and < 0.14
		else
			adjustment = 0;
		
		masterRight.set(speed - rightTurn + leftTurn - adjustment);
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
		System.out.println("Speed " + speed); // .5
		System.out.println("multiplier " + multiplier); // 6.5
		System.out.println("distance" + inches); // 80
		double	currentRotationRate = rotateToAngleRate;
		System.out.println("CurrentRR: " + currentRotationRate);
		if(masterLeft.getEncPosition()/1024*Math.PI*4 > (inches*multiplier)){
			System.out.println("Done");
			stopDrive();
			doneDriving = true;
		}else{
			turnDrive(.65, currentRotationRate);
			System.out.println("Navx = " + gyro.getAngle());
			doneDriving = false;
		}
		return doneDriving;
	}	
		
	public boolean driveDistanceStraightRightEncoder(double speed, double inches) {
		boolean doneDriving = false;
		System.out.println("Speed " + speed); // .5
		System.out.println("multiplier " + multiplier); // 6.5
		System.out.println("distance" + inches); // 80
		double	currentRotationRate = rotateToAngleRate;
		System.out.println("CurrentRR: " + currentRotationRate);
		if(masterRight.getEncPosition()/1024*Math.PI*4 > (inches*multiplier)){
			System.out.println("Done");
			stopDrive();
			doneDriving = true;
		}else{
			turnDrive(.65, currentRotationRate);
			System.out.println("Navx = " + gyro.getAngle());
			doneDriving = false;
		}
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
		masterLeft.set(leftMotorSpeed);
	}

	public boolean turnAngle(double turnAngle){
		double angle = turnAngle;
		boolean doneTurning = false;
		double	currentRotationRate = rotateToAngleRate;
		 if (Math.abs(angle - gyro.getAngle()) < .6 && Math.abs(currentRotationRate) < .3){
				currentRotationRate = 0;
				turnDrive(0.0, 0);
				//turnController.disable();
				doneTurning = true;	
			}
		 else
		 {
			 try {
				 turnDrive(0.0, currentRotationRate);
				 //Timer.delay(0.004);
				 System.out.println("Navx " + gyro.getAngle());
				 System.out.println(" currentRotationRate  = " + currentRotationRate);
				 System.out.println(" Math.abs(angle - hrs.getAngle()) = " +  Math.abs(angle -gyro.getAngle()) );
			 	} catch ( RuntimeException ex ) {
			 		DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
			 	}
		 }
		 return doneTurning;
	}

//	private void TurnAngle(){
//		currentRotationRate = rotateToAngleRate;
//		try {
//			turnDrive(0.0, currentRotationRate);
//			System.out.println(gyro.getAngle());
//		} catch (RuntimeException ex) {
//			DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
//		}
//	}
//	
//	public void turnAngle(double turnAngle) {
//		if (state == States.GET_CAMERA_ANGLE){ 
//			System.out.println("++++ GetCameraAngle Turn Angle =  " + turnAngle);
//			state = States.SET_PID_LOOP;
//		}
//		if (state == States.SET_PID_LOOP){
//				setTurnController();
//				System.out.println("++++++++++++++++++++++++Set Pid Loop ************** ");
//				state = States.TURN_ANGLE;
//		}
//		if (state == States.TURN_ANGLE) {
//			System.out.println("++++++++++++++++++++++++Turn Angle " + turnAngle + " navx angle " + gyro.getAngle());
//			System.out.println("++++++++++++++++++++++++currentRotationRate " + currentRotationRate);
//			TurnAngle();
//			if (Math.abs(currentRotationRate) < .23 && Math.abs(turnAngle - gyro.getAngle()) < .3) {
//				currentRotationRate = 0;
//				TurnAngle();
//				turnController.disable();
//				state = States.DONE;
//			}
//		}
//	}

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}