package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class DriveTrain {
	DriverStation d = DriverStation.getInstance();
	DoubleSolenoid shifter = new DoubleSolenoid(1, 2);
	Constants constants;
	CANTalon Front_Right;
	CANTalon Front_Left;
	CANTalon Back_Right;
	CANTalon Back_Left;
	AHRS gyro = new AHRS(Port.kMXP);
	
	public DriveTrain(Constants c){
		constants = c;
		Front_Right = new CANTalon(constants.DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT);
		Front_Left = new CANTalon(constants.DRIVETRAIN_MASTER_LEFT_MOTOR_PORT);
		Back_Right = new CANTalon(constants.DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT);
		Back_Left = new CANTalon(constants.DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT);
		
	}	
	public void drive(double speed){	//drives forward
		Front_Right.set(speed);
		Front_Left.set(-speed);
		Back_Right.set(speed);
		Back_Left.set(-speed);
	}
	public void driveTimeStraight(double speed, double time){	//drives forward for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			drive(speed);
		}
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
	public void turnToAngle(int angle){
		gyro.reset();
		double currentAngle = gyro.getYaw();
		while(currentAngle!=angle){
			if(angle > 2 ){
				turnRight(1);
			}else if(angle < -2){
				turnLeft(1);
			}
		}
	}
}
