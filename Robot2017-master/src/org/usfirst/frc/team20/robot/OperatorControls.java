//Author: Rahul Shah and Ronak Parida
package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Controller operatorJoy;
	FuelTank tank;
	GearMechanism gear;
	FlyWheel flywheel;
	GroundCollector collector;
	BobbysGearMechanism bobby;
	boolean shooting;
	boolean tankToFlywheel;	
	boolean getStartTime = false;
	boolean collectingGear = false;
	double startTime;

	public OperatorControls(FuelTank h, GearMechanism g, FlyWheel f, GroundCollector c, BobbysGearMechanism b) {
		operatorJoy = new Controller(1);
		tank = h;
		gear = g;
		flywheel = f;
		collector = c;
		bobby = b;
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);
		shooting = false;
		tankToFlywheel = false;
	}

	public void operatorControls() {
		if (operatorJoy.getButtonY()) {
			tank.retractAgitator();
			collector.intake(0.85);
			tank.tankMotorIntoTank(0.95);
		}
		if (operatorJoy.getButtonA()) {
			collector.outtake(1);
		}
//		if (operatorJoy.getButtonDRight()) {
//			gear.gearFlapIn();
//		}
//		if (operatorJoy.getButtonDLeft()) {
//			gear.gearFlapOut();
//		}
		if (operatorJoy.getButtonDRight()) {
			bobby.extend();
		}
		if (operatorJoy.getButtonDLeft()) {
			bobby.retract();
		}
		if (operatorJoy.getButtonDUp()){	//gear collector collect
			bobby.extend();
			bobby.intake();
//			if (bobby.intake()){
//				bobby.retract();
//				collector.collector.set(0.0); //speed was 0.2
//				collectingGear = true;
//			}
		}
//		if(collectingGear){
//			if(!getStartTime){
//				startTime = Timer.getFPGATimestamp();
//				getStartTime = true;
//			}
//			if(Timer.getFPGATimestamp() - startTime > Constants.WAIT_GEAR_TIME){
//				bobby.retract();
//				collector.collector.set(0.0); //speed was 0.2
//				collectingGear = false;
//			}
//		}
		if (operatorJoy.getButtonDDown()){	//gear collector hold for placement
			bobby.extend();
			bobby.outtake();
		}
		if (operatorJoy.getButtonX() || operatorJoy.getButtonB()) {
			collector.stopCollector();
			tank.stopTank();
			tankToFlywheel = false;
			collectingGear = false;
			getStartTime = false;
		}
		if (operatorJoy.getButtonStart()) {
			flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
		}
		if (operatorJoy.getRightTriggerAxis() > 0) {
			if (flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)) { 
				collector.intake(1.0);
				tank.tankMotorIntoFlywheel(0.4);
			}
			shooting = true;
		}
		if (tankToFlywheel || shooting) {
			tank.runAgitator();
		}
		if (operatorJoy.getButtonBack()) {
			flywheel.stopFlywheel();
			tank.stopTank();
			collector.stopCollector();
			shooting = false;
			tankToFlywheel = false;
			getStartTime = false;
			collectingGear = false;
			tank.retractAgitator();
		}
	}
}
