package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hopper {
	CANTalon hopperIntake;
	private DoubleSolenoid agitatorPistons;

	public Hopper(){
		hopperIntake = new CANTalon(Constants.HOPPER_COLLECTOR_MOTOR_PORT);
		agitatorPistons = new DoubleSolenoid(Constants.HOPPER_EXTEND_PORT,
				Constants.HOPPER_RETRACT_PORT);
	}
	
	public void hopperMotorRun(double speed){
		hopperIntake.set(speed);
	}
	public void hopperMotorStop(){
		hopperIntake.set(0);
	}
	public void retractCollector() {
		agitatorPistons.set(DoubleSolenoid.Value.kReverse);
	}
	public void actuateCollectors() {
		agitatorPistons.set(DoubleSolenoid.Value.kForward);
	}
	public void neturalCollectors() {
		agitatorPistons.set(DoubleSolenoid.Value.kOff);
	}	
}
