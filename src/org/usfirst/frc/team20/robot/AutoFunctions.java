package org.usfirst.frc.team20.robot;

public class AutoFunctions {
	DriveTrain drive;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	
	public AutoFunctions(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT){
		drive = d;
		flywheel = f;
		vision = vT;
		collector = c;	
	}

	public void crossBaseline(){
		drive.driveDistanceStraight(1, 100);	
	}
	public void toMiddlePeg(){
		drive.driveDistanceStraight(1, 94);
	}	
	public void toSidePeg(){
		drive.driveDistanceStraight(1, 90); //TODO get about the number of inches
		vision.updateImage();
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}	
	public void middlePegToHopperRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void middlePegToHopperBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void middlePegToBoilerRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void middlePegToBoilerBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerRed(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerBlue(){
		drive.driveDistanceStraight(-1, 5);
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void toHopperRed(){
		drive.driveDistanceStraight(1, 95);
		drive.turnAngle(-90);
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void toHopperBlue(){
		drive.driveDistanceStraight(1, 95); 
		drive.turnAngle(90);
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void hopperToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void hopperToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void wallToBoilerRed(){
		drive.driveDistanceStraight(1, 45);
		drive.turnAngle(-135);
		drive.driveDistanceStraight(0.5, 45);
	}
	public void wallToBoilerBlue(){
		drive.driveDistanceStraight(1, 45);
		drive.turnAngle(135);
		drive.driveDistanceStraight(0.5, 45);
	}
	public void shoot(){
		flywheel.shootWithEncoders(3000.0, Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, 
				Constants.FLYWHEEL_D);
		collector.intake(1);
	}
}