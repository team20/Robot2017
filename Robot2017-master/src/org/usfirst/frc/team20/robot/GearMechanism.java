package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearMechanism {
	boolean state = false; // false is no gear
	DoubleSolenoid gearFlap;
//	DigitalInput gearBumpSwitch1;
	FlyWheel flywheel;
	OperatorControls operator;
	boolean pressed = false;
	boolean done = false;
	int counter = 0;
	boolean automated = false;

	public GearMechanism(FlyWheel f, OperatorControls o) {
		gearFlap = new DoubleSolenoid(Constants.GEAR_EXTEND_PORT, Constants.GEAR_RETRACT_PORT);
//		gearBumpSwitch1 = new DigitalInput(Constants.GEAR_BUMP_SWITCH_PORT_ONE);
		flywheel = f;
		operator = o;
	}

	public void gearFlapOut() {
		gearFlap.set(DoubleSolenoid.Value.kReverse);
	}

	public void gearFlapIn() {
		gearFlap.set(DoubleSolenoid.Value.kForward);
	}

	public boolean checkGear() {
//		if (gearBumpSwitch1.get() == false) {
//			return true;
//		} else {
			return false;
//		}
	}

//	public void moveFlaps() {
//		if (gearBumpSwitch1.get() == false && automated) {
//			gearFlapIn();
//			counter = 0;
//		}
//		if (gearBumpSwitch1.get() == true && automated) {
//			counter++;
//			if (counter > 8) {
//				gearFlapOut();
//			}
//		}
	//}
}
