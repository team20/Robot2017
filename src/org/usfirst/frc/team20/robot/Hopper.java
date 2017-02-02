package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hopper {
	CANTalon hopperOuttake;
	private DoubleSolenoid agitatorPistons;

	public Hopper(){
		hopperOuttake = new CANTalon(Constants.HOPPER_COLLECTOR_MOTOR_PORT);
		agitatorPistons = new DoubleSolenoid(Constants.HOPPER_EXTEND_PORT,
				Constants.HOPPER_RETRACT_PORT);
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
