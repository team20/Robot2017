package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Controller operatorJoy;
	FuelTank tank;
	GearMechanism gear;
	FlyWheel flywheel;
	GroundCollector collector;
	boolean shooting;
	boolean tankToFlywheel;

	public OperatorControls(FuelTank h, GearMechanism g, FlyWheel f, GroundCollector c) {
		operatorJoy = new Controller(1);
		tank = h;
		gear = g;
		flywheel = f;
		collector = c;
		flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);
		shooting = false;
		tankToFlywheel = false;
	}

	public void operatorControls() {
		gear.moveFlaps();
		if (operatorJoy.getButtonY()) {
			tank.retractAgitator();
			collector.intake(.9);
			tank.tankMotorIntoTank(.95);
		}
		if (operatorJoy.getButtonA()) {
			collector.outtake(1);
		}
		if(operatorJoy.getButtonDUp()){
			gear.automated = true;
		}
		if(operatorJoy.getButtonDDown()){
			gear.automated = false;
		}
		if(operatorJoy.getButtonDRight() && !gear.automated){
			gear.gearFlapIn();
		}
		if(operatorJoy.getButtonDLeft() && !gear.automated){
			gear.gearFlapOut();
		}
		
//		if (operatorJoy.getButtonDUp()){
//			flywheel.shootWithEncoders(5000);			SECOND FLYWHEEL SPEED *** DOES NOT WORK ***
//		}
		if (operatorJoy.getButtonX() || operatorJoy.getButtonB()) {
			collector.stopCollector();
			tank.stopTank();
			tankToFlywheel = false;
		}
		if (operatorJoy.getButtonStart()) {
			flywheel.shootWithEncoders(Constants.FLYWHEEL_SPEED);
		}
		//TODO ask Roland what this is
//		if(flywheel.flywheelMaster.getOutputCurrent()>50){
//			flywheel.setPID(0,0,0,Constants.FLYWHEEL_F);
//		}else{
//			flywheel.setPID(Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, Constants.FLYWHEEL_D, Constants.FLYWHEEL_F);
//		}
		if (operatorJoy.getRightTriggerAxis() > 0) {
			if (flywheel.flywheelReady(Constants.FLYWHEEL_SPEED)) { // was Constants.flywheelSpeed
				collector.intake(1);
				tank.tankMotorIntoFlywheel(0.35); // was 1
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
			tank.retractAgitator();
		}
	}
}
