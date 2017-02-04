package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hopper {
	CANTalon hopperOuttake;
	private DoubleSolenoid agitatorPistons;

	public Hopper(){
		hopperOuttake = new CANTalon(Constants.HOPPER_COLLECTOR_MOTOR_PORT);
		agitatorPistons = new DoubleSolenoid(1, 0);
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
	public void retractAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kReverse);
	}
	public void actuateAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kForward);
	}
	public void neturalAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kOff);
	}	
}