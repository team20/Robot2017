package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class OperatorControls {
	Controller operatorJoy;
	Hopper hopper;
	GearMechanism gear;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	DriverStation station;
	double initialMatchTime;
	boolean hopperToFlywheel;
	boolean shooting;
	public OperatorControls(Hopper h, GearMechanism g, FlyWheel f, VisionTargeting v, GroundCollector ground){
		operatorJoy = new Controller(1);
		hopper = h;
		gear = g;
		flywheel = f;
		vision = v;
		collector = ground;
		station = DriverStation.getInstance();
		initialMatchTime = station.getMatchTime();
		hopperToFlywheel = false;
		shooting = false;
	}

	public void operatorControls(){
		if (operatorJoy.getButtonA()) {
			if(shooting){
				if(2900 < flywheel.flywheelSpeed() && flywheel.flywheelSpeed() < 3100){
					collector.intake(1);
				}else{
					
				}
			}else{
				  collector.intake(1);
			}
		}
		
		if (operatorJoy.getButtonB()) {
			collector.outtake(1);
		}
		if (operatorJoy.getButtonX()) {
			hopper.hopperMotorIntoFlywheel(1);
			hopperToFlywheel = true;
		}
		if (hopperToFlywheel) {
			double currentMatchTime = station.getMatchTime();
			if(initialMatchTime < 0){
				initialMatchTime = currentMatchTime;
			}
			System.out.println(initialMatchTime + "              " + currentMatchTime);
			if(initialMatchTime - 0.25 < currentMatchTime){
				System.out.println("In the If");
				hopper.retractAgitator();
			}else{
				if(initialMatchTime - 0.4 > currentMatchTime){
					initialMatchTime = currentMatchTime;
				}
				System.out.println("In the else");
				hopper.actuateAgitator();
			}
		}
		if (operatorJoy.getButtonY()) {
			hopper.hopperMotorIntoHopper(1);
		}
		if (operatorJoy.getButtonRightBumper()) {
			gear.gearFlapIn();
		}
		if (operatorJoy.getButtonLeftBumper()) {
			gear.gearFlapOut();
		}
		if (operatorJoy.getButtonDUp()) {
			flywheel.setPID(0.0003, 0.0, 0.0, 0.165);
			shooting = true;
			flywheel.shootWithEncoders(3000.0);
		}
		if(shooting){
		}
		if (operatorJoy.getButtonBack()) {
			flywheel.stopFlywheel();
			hopper.stopHopper();
			collector.stopCollector();
			shooting = false;
			hopperToFlywheel = false;
		}

	}
}
