package org.usfirst.frc.team20.robot;

public class AutoFunctions {
	DriveTrain drive;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	Hopper hopper;
	GearMechanism gear;
	public AutoFunctions(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT, Hopper h, GearMechanism g){
		drive = d;
		flywheel = f;
		vision = vT;
		collector = c;	
		hopper = h;
		gear = g;
	}

	public void crossBaseline(){
		drive.driveDistanceStraightLeftEncoder(1, 100);	
	}
	public void toMiddlePeg(){
		drive.driveDistanceStraightLeftEncoder(1, 94);
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
		drive.driveDistanceStraightLeftEncoder(1, 90); //TODO get about the number of inches
		drive.turnAngle(45);	//TODO get approx. angle
		vision.updateImage();
		 drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void toRightPeg(){
		drive.driveDistanceStraightLeftEncoder(1, 90); //TODO get about the number of inches
		drive.turnAngle(-45);	//TODO get approx. angle
		vision.updateImage();
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());				
	}
	public void middlePegToHopperRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void middlePegToHopperBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void middlePegToBoilerRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void middlePegToBoilerBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerRed(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerBlue(){
		drive.driveDistanceStraightLeftEncoder(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void toHopperRed(){
		drive.driveDistanceStraightLeftEncoder(1, 95);
		drive.turnAngle(-90);
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void toHopperBlue(){
		drive.driveDistanceStraightLeftEncoder(1, 95); 
		drive.turnAngle(90);
		drive.driveDistanceStraightLeftEncoder(1, 15); //TODO get about the number of inches
	}
	public void hopperToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void hopperToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraightLeftEncoder(0.5, vision.getSecondDistance());
	}
	public void shoot(double RPMS){
		boolean shooting = true;
		flywheel.shootWithEncoders(RPMS);
		collector.intake(1);
		hopper.hopperMotorIntoFlywheel(1);
		if(shooting){
			hopper.runAgitator();
		}
	}
	public void stopFlywheel(){
		flywheel.stopFlywheel();
	}
}