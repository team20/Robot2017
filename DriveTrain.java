package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

public class DriveTrain {
	DriverStation d;
	CANTalon[] talons = new CANTalon[4]; // contains the CANTalons
	DoubleSolenoid shifter = new DoubleSolenoid(moduleNumber, forwardChannel, reverseChannel);// Figure out what the Channels are
	public DriveTrain(int[] CANTalonPorts, DriverStation d){
		for(int i = 0; i < CANTalonPorts.length; i++){
			talons[i] = new CANTalon(CANTalonPorts[i]); // Assigns Port Numbers to the CANTalons
		}
		this.d = d;
	}
	
	
	public void drive(double speed){
		for (int i = 0; i < talons.length; i++){
			if(i%2 == 1){
				talons[i].set(-speed);
			}else{
				talons[i].set(speed);
			}
		}
	}
	public void driveTimeStraight(double speed, double time){
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			drive(speed);
		}
	}
	public void turnRight(double speed){
		for (int i = 0; i < talons.length; i++){
			talons[i].set(-speed);
		}
	}
	public void driveTimeRight(double speed, double time){
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			turnRight(speed);
		}
	}
	public void turnLeft(double speed){
		for (int i = 0; i < talons.length; i++){
			talons[i].set(speed);
		}
	}
	public void driveTimeLeft(double speed, double time){
		double startTime = d.getMatchTime();
		double endTime = startTime+time;
		while(d.getMatchTime()<endTime){
			turnLeft(speed);
		}
	}
	
	public void shiftHigh(){
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void shiftLow(){
		shifter.set(DoubleSolenoid.Value.kForward);
	}
}
