package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class DriveTrain implements PIDOutput{
	DriverStation d = DriverStation.getInstance();
	DoubleSolenoid shifter = new DoubleSolenoid(1, 2);
	Constants constants;
	CANTalon Front_Right;
	CANTalon Front_Left;
	CANTalon Back_Right;
	CANTalon Back_Left;
	double startingAngle = 0, adjustment;
	AHRS gyro = new AHRS(Port.kMXP);
	PIDController turnController;
    double rotateToAngleRate;
    static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    static final double kToleranceDegrees = 2.0f;
	
	public DriveTrain(Constants c){
		constants = c;
		Front_Right = new CANTalon(constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		Front_Left = new CANTalon(constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		Back_Right = new CANTalon(constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT);
		Back_Left = new CANTalon(constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT);
		

        turnController = new PIDController(kP, kI, kD, kF, gyro, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
		
	}	
	public void drive(double speed, double rightTurn, double leftTurn){	//drives forward
		
		if(speed < 0.1 && speed > -0.1){
			gyro.reset();
			startingAngle = gyro.getYaw();
			adjustment = 0;
		}
		
		if(gyro.getYaw() > 0.1)
			adjustment = (speed*0.125); //0.125 can be changed so that it is > 0.1 and < 0.14
		else if(gyro.getYaw() < -0.1)
			adjustment = -(speed*0.125);//0.125 can be changed so that it is > 0.1 and < 0.14
		else
			adjustment = 0;
		
		Front_Right.set(speed - rightTurn + leftTurn - adjustment);
		Front_Left.set(-speed + leftTurn - rightTurn);
		Back_Right.set(speed - rightTurn + leftTurn - adjustment);
		Back_Left.set(-speed + leftTurn - rightTurn);
	}
	public void driveTimeStraight(double speed, double time){	//drives forward for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			drive(speed, 0, 0);
		}
	}
	public void driveDistanceStraight(double speed, double distance){
		
	}
	public void turnRight(double speed){	//turns right
		Front_Right.set(-speed);
		Front_Left.set(-speed);
		Back_Right.set(-speed);
		Back_Left.set(-speed);
	}
	public void driveTimeRight(double speed, double time){	//turns right for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			turnRight(speed);
		}
	}
	public void turnLeft(double speed){		//turns left
		Front_Right.set(speed);
		Front_Left.set(speed);
		Back_Right.set(speed);
		Back_Left.set(speed);
	}
	public void driveTimeLeft(double speed, double time){	//turns left for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			turnLeft(speed);
		}
	}
	
	public void shiftHigh(){	//shifts into high gear ratio
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void shiftLow(){		//shifts into low gear ratio
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void turnAngle(double angle){
		turnController.setSetpoint(angle);
	}
	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
