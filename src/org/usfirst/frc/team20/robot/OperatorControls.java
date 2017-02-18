package org.usfirst.frc.team20.robot;

public class OperatorControls {
	Controller operatorJoy;
	FuelTank tank;
	GearMechanism gear;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	boolean shooting;
	boolean tankToFlywheel;

	public OperatorControls(FuelTank h, GearMechanism g, FlyWheel f, VisionTargeting v, GroundCollector c){
		operatorJoy = new Controller(1);
		tank = h;
		gear = g;
		flywheel = f;
		vision = v;
		collector = c;
		flywheel.setPID(0.0003, 0.0, 0.0, 0.165);
		shooting = false;
		tankToFlywheel = false;
	}

	public void operatorControls(){
		if (operatorJoy.getButtonA()) {
			collector.intake(1);
			tank.tankMotorIntoTank(1);
		}
		if (operatorJoy.getButtonB()) {
			collector.outtake(1);
			tank.tankMotorIntoFlywheel(1);
			tankToFlywheel = true;
		}
		if(operatorJoy.getButtonX()){
			collector.stopCollector();
			tank.stopTank();
			tankToFlywheel = false;
		}
//		if (operatorJoy.getButtonRightBumper()) {
//			gear.gearFlapIn();
//		}
//		if (operatorJoy.getButtonLeftBumper()) {
//			gear.gearFlapOut();
//		}
		if (operatorJoy.getButtonY()) {
			flywheel.shootWithEncoders(3000.0);
			collector.intake(1);
			tank.tankMotorIntoFlywheel(1);
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
		}
	}
}
