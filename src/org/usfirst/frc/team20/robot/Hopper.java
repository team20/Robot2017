package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

public class Hopper {
	CANTalon hopperOuttake;
	private DoubleSolenoid agitatorPistons;
	DriverStation station;
	double initialMatchTime;
	boolean hopperToFlywheel;
	
	public Hopper(){
		hopperOuttake = new CANTalon(Constants.HOPPER_COLLECTOR_MOTOR_PORT);
		agitatorPistons = new DoubleSolenoid(1, 0);
		station = DriverStation.getInstance();
		initialMatchTime = station.getMatchTime();
		hopperToFlywheel = false;
	}
	
	public void hopperMotorIntoFlywheel(double speed){
		hopperOuttake.set(-speed);
	}	
	public void hopperMotorIntoHopper(double speed){
		hopperOuttake.set(speed);
	}
	public void stopHopper(){
		hopperOuttake.set(0);
	}
	private void retractAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kReverse);
	}
	private void actuateAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kForward);
	}
	public void runAgitator(){
		double currentMatchTime = station.getMatchTime();
		if(initialMatchTime < 0){
			initialMatchTime = currentMatchTime;
		}
		System.out.println(initialMatchTime + "              " + currentMatchTime);
		if(initialMatchTime - 0.25 < currentMatchTime){
			System.out.println("In the If");
			retractAgitator();
		}else{
			if(initialMatchTime - 0.4 > currentMatchTime){
				initialMatchTime = currentMatchTime;
			}
			actuateAgitator();
		}
	}
	public void neturalAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kOff);
	}	
}