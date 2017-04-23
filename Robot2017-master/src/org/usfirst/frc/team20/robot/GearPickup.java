package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearPickup {
	DoubleSolenoid bobbyPiston;
	
	public GearPickup() {
		bobbyPiston = new DoubleSolenoid(Constants.FLOOR_EXTEND_PORT, Constants.FLOOR_RETRACT_PORT);
	}
	
	public void retract(){
		bobbyPiston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void extend(){
		bobbyPiston.set(DoubleSolenoid.Value.kForward);
	}
}