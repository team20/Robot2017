package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BobbysGearMechanism {
	DoubleSolenoid bobbyPiston;
	OperatorControls operator;
	GroundCollector collector;
	
	public BobbysGearMechanism(OperatorControls o, GroundCollector c) {
		bobbyPiston = new DoubleSolenoid(Constants.GEAR_EXTEND_PORT, Constants.GEAR_RETRACT_PORT);
		operator = o;
		collector = c;
	}
	
	public void retract(){
		bobbyPiston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void extend(){
		bobbyPiston.set(DoubleSolenoid.Value.kForward);
	}
}