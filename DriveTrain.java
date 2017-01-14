package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

public class DriveTrain {
	DriverStation d = DriverStation.getInstance();
	CANTalon[] talons = new CANTalon[4]; //TODO contains the CANTalons
	DoubleSolenoid shifter = new DoubleSolenoid(1, 2);
	Constants constants;
	public DriveTrain(int[] CANTalonPorts, Constants c){
		constants = c;
		for(int i = 0; i < CANTalonPorts.length; i++){
			talons[i] = new CANTalon(CANTalonPorts[i]); // Assigns Port Numbers to the CANTalons
		}
	}	
	public void drive(double speed){	//drives forward
		for (int i = 0; i < talons.length; i++){
			if(i%2 == 1){
				talons[i].set(-speed);
			}else{
				talons[i].set(speed);
			}
		}
	}
	public void driveTimeStraight(double speed, double time){	//drives forward for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			drive(speed);
		}
	}
	public void turnRight(double speed){	//turns right
		for (int i = 0; i < talons.length; i++){
			talons[i].set(-speed);
		}
	}
	public void driveTimeRight(double speed, double time){	//turns right for a specific time
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			turnRight(speed);
		}
	}
	public void turnLeft(double speed){		//turns left
		for (int i = 0; i < talons.length; i++){
			talons[i].set(speed);
		}
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
}
