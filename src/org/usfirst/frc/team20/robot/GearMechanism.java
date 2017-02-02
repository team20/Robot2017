package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearMechanism {
	DoubleSolenoid gearFlap;
	DigitalInput gearBumpSwitch1, gearBumpSwitch2;
	boolean haveGear = false;
	
	public GearMechanism(){
		gearFlap = new DoubleSolenoid(Constants.GEAR_EXTEND_PORT, Constants.GEAR_RETRACT_PORT);
		gearBumpSwitch1 = new DigitalInput(Constants.GEAR_BUMP_SWITCH_PORT_ONE);
		gearBumpSwitch2 = new DigitalInput(Constants.GEAR_BUMP_SWITCH_PORT_TWO);
	}
	
	public void gearFlapOut(){
		gearFlap.set(DoubleSolenoid.Value.kForward);
	}
	public void gearFlapIn(){
		gearFlap.set(DoubleSolenoid.Value.kReverse);
	}
	public void checkGear(){
		if(gearBumpSwitch1.get() || gearBumpSwitch2.get()){
			haveGear = true;
		}
		else{
			haveGear = false;
		}
	}
}
