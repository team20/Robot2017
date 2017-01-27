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
		drive.driveDistanceStraight(1, 20);	//TODO get about the number of inches
	}
	public void toMiddlePeg(){
		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
		vision.updateImage();
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void toSidePeg(){
		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
		vision.updateImage();
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
//	public void toLeftPegRed(){
//		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
//		vision.updateImage();
//		drive.turnAngle(vision.getFirstAngle());
//		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
//		drive.turnAngle(vision.getSecondAngle());
//		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
//	}
//	public void toLeftPegBlue(){
//		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
//		vision.updateImage();
//		drive.turnAngle(vision.getFirstAngle());
//		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
//		drive.turnAngle(vision.getSecondAngle());
//		drive.driveDistanceStraight(0.5, vision.getSecondDistance());		
//	}
//	public void toRightPegRed(){
//		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
//		vision.updateImage();
//		drive.turnAngle(vision.getFirstAngle());
//		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
//		drive.turnAngle(vision.getSecondAngle());
//		drive.driveDistanceStraight(0.5, vision.getSecondDistance());				
//	}
//	public void toRightPegBlue(){
//		drive.driveDistanceStraight(1, 5); //TODO get about the number of inches
//		vision.updateImage();
//		drive.turnAngle(vision.getFirstAngle());
//		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
//		drive.turnAngle(vision.getSecondAngle());
//		drive.driveDistanceStraight(0.5, vision.getSecondDistance());		
//	}
	public void middlePegToHopperRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void middlePegToHopperBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void leftPegToHopperBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void rightPegToHopperBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void middlePegToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void middlePegToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void leftPegToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void rightPegToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.turnAngle(vision.getFirstAngle());
		drive.driveDistanceStraight(0.5, vision.getFirstDistance());
		drive.turnAngle(vision.getSecondAngle());
		drive.driveDistanceStraight(0.5, vision.getSecondDistance());
	}
	public void toHopperRed(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
	}
	public void toHopperBlue(){
		drive.turnAngle(20);	//TODO calculate angle
		drive.driveDistanceStraight(1, 15); //TODO get about the number of inches
		drive.turnAngle(20);	//TODO calculate angle
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
		drive.turnAngle(20);	//TODO calculate angle
	}
	public void wallToBoilerBlue(){
		drive.turnAngle(20);	//TODO calculate angle
	}
	public void shoot(){
		flywheel.shootWithEncoders(5000.0, Constants.FLYWHEEL_P, Constants.FLYWHEEL_I, 
				Constants.FLYWHEEL_D);
		collector.intake(1);
	}
}