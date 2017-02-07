package org.usfirst.frc.team20.robot;

public class AutoFunctionsRightEncoder {
	DriveTrain drive;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	Hopper hopper;
	GearMechanism gear;
	public AutoFunctionsRightEncoder(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT, Hopper h, GearMechanism g){
		drive = d;
		flywheel = f;
		vision = vT;
		collector = c;	
		hopper = h;
		gear = g;
	}

	public void crossBaseline(){
		drive.driveDistanceStraightRightEncoder(1, 100);	
	}
	public void toMiddlePeg(){
		drive.driveDistanceStraightRightEncoder(1, 94);
	}	
//	public void toSidePeg(){
//		drive.driveDistanceStraight(1, 90); //TODO get about the number of inches
//		vision.updateImage();
//		drive.turnAngle(vision.getFirstAngle());
//		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
//		drive.turnAngle(vision.getSecondAngle());
//		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
//	}	
	public void toLeftPeg(){
		drive.driveDistanceStraightRightEncoder(1, 90); //TODO get about the number of inches
		drive.turnAngle(45);	//TODO get approx. angle
		vision.updateImage();
		 drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void toRightPeg(){
		drive.driveDistanceStraightRightEncoder(1, 90); //TODO get about the number of inches
		drive.turnAngle(-45);	//TODO get approx. angle
		vision.updateImage();
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());				
	}
	public void middlePegToHopperRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void middlePegToHopperBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void middlePegToBoilerRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void middlePegToBoilerBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerRed(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerBlue(){
		drive.driveDistanceStraightRightEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void toHopperRed(){
		drive.driveDistanceStraightRightEncoder(1, 95);
		drive.turnAngle(-90);
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void toHopperBlue(){
		drive.driveDistanceStraightRightEncoder(1, 95); 
		drive.turnAngle(90);
		drive.driveDistanceStraightRightEncoder(1, 15); //TODO get about the number of inches
	}
	public void hopperToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void hopperToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightRightEncoder(0.5, vision.getSecondDistance());
	}
	public void wallToBoilerRed(){
		drive.driveDistanceStraightRightEncoder(1, 45);
		drive.turnAngle(-135);
		drive.driveDistanceStraightRightEncoder(0.5, 45);
	}
	public void wallToBoilerBlue(){
		drive.driveDistanceStraightRightEncoder(1, 45);
		drive.turnAngle(135);
		drive.driveDistanceStraightRightEncoder(0.5, 45);
	}

	public void shoot(double RPMS){
		boolean shooting = true;
		flywheel.shootWithEncoders(RPMS);
		collector.intake(1);
		hopper.hopperMotorIntoFlywheel(1);
		while(shooting){
			hopper.actuateAgitator(); //TODO make like robot.java
			hopper.retractAgitator();
		}
	}
	public void stopFlywheel(){
		flywheel.stopFlywheel();
	}
}