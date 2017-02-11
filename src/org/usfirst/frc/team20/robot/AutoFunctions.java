package org.usfirst.frc.team20.robot;

public class AutoFunctions {
	DriveTrain drive;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	Hopper hopper;
	GearMechanism gear;
	Util util;
	int state;
	int group;
	double distanceFromCamera = 0.0;
	double angleFromCamera = 0.0;
	double distanceFromCameraTwo = 0.0;
	double angleFromCameraTwo = 0.0;
	
	public AutoFunctions(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT, Hopper h, GearMechanism g){
		drive = d;
		flywheel = f;
		vision = vT;
		collector = c;	
		hopper = h;
		gear = g;
		util = new Util();
	}
	private void runBeforeTargeting(){
		String getSocketData;
		drive.gyro.reset();
		//distanceFromCamera
		getSocketData = util.getCameraAngle();
		String[] values = getSocketData.split("\\*");
		distanceFromCamera = Double.parseDouble(values[0]);
		angleFromCamera = Double.parseDouble(values[1]);
		distanceFromCameraTwo = Double.parseDouble(values[2]);
		angleFromCameraTwo = Double.parseDouble(values[3]);
		drive.turnController.setSetpoint(angleFromCamera);
		drive.turnController.enable();
	}
	private void hardDistanceHardAngle(double speed, double hardDistance, double hardAngle){
		if(state == States.GO_DISTANCE && group == Groups.GROUP_1){
			drive.driveDistanceStraightLeftEncoder(speed, hardDistance);
			if(drive.driveDistanceStraightLeftEncoder(speed, hardDistance)){
				drive.stopDrive();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_1;
			}
		}
		if(state == States.TURN_ANGLE && group == Groups.GROUP_1){
			drive.turnAngle(hardAngle);
			if(drive.turnAngle(hardAngle)){
				drive.stopDrive();
				runBeforeTargeting();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_2;
			}
		}
	}
	private void visionTarget(){
		if(state == States.TURN_ANGLE && group == Groups.GROUP_2){
			drive.turnAngle(angleFromCamera);
			if(drive.turnAngle(angleFromCamera)){
				drive.stopDrive();
				state = States.GO_DISTANCE;
				group = Groups.GROUP_2;
			}
		}
		if(state == States.GO_DISTANCE && group == Groups.GROUP_2){
			drive.driveDistanceStraightLeftEncoder(0.8, distanceFromCamera);
			if(drive.driveDistanceStraightLeftEncoder(0.8, distanceFromCamera)){
				drive.stopDrive();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_3;
			}
		}
		if(state == States.TURN_ANGLE && group == Groups.GROUP_3){
			drive.turnAngle(angleFromCameraTwo);
			if(drive.turnAngle(angleFromCameraTwo)){
				drive.stopDrive();
				state = States.GO_DISTANCE;
				group = Groups.GROUP_3;
			}
		}
		if(state == States.GO_DISTANCE && group == Groups.GROUP_3){
			drive.driveDistanceStraightLeftEncoder(0.8, distanceFromCameraTwo);
			if(drive.driveDistanceStraightLeftEncoder(0.8, distanceFromCameraTwo)){
				drive.stopDrive();
				state = States.WAIT_FOR_GEAR;
			}
		}
	}
	public void backUpFromBoilerTurnAndTarget(){
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromBoilerDistance,
				AutoConstants.boilerToSidePegAngle);
		if(state == States.TURN_ANGLE && group == Groups.GROUP_2){
			visionTarget();
		}
		if(state == States.WAIT_FOR_GEAR){
			if(gear.haveGear){
				state = States.DONE;
			}
		}

	}
	public void autoMode1(){
		runBeforeTargeting();
		state = States.TURN_ANGLE;
		if(state == States.TURN_ANGLE){
			System.out.println("**********************************angleFromCamera = "   + angleFromCamera);
			System.out.println("**********************************Navx = "   + drive.gyro.getAngle());
			if(drive.turnAngle(angleFromCamera)){
				state = States.GO_DISTANCE;
				//set encoder to zero so it can calculate distance
				drive.masterLeft.setEncPosition(0);
			}
			if(state == States.GO_DISTANCE){
				System.out.println("*******lkjljlkjlkj;jl;dj;kafjf;ad************Go distance parameters");
				if (drive.driveDistanceStraightLeftEncoder(0.8, distanceFromCamera)){
					state = States.DONE;
					System.out.println("NavX Angle at End: " + drive.gyro.getAngle());
				}
			}
		}
	}
	public void crossBaseline(){
		state = States.GO_DISTANCE;
		if(state == States.GO_DISTANCE){
			drive.driveDistanceStraightLeftEncoder(1, AutoConstants.crossBaselineDistance);
			if(drive.driveDistanceStraightLeftEncoder(1, AutoConstants.crossBaselineDistance)){
				drive.stopDrive();
				state = States.DONE;
			}
		}
	}
	public void toMiddlePeg(){
		if(state == States.GO_DISTANCE){
			drive.driveDistanceStraightLeftEncoder(0.8, AutoConstants.middlePegDistance);
			if(drive.driveDistanceStraightLeftEncoder(0.8, AutoConstants.middlePegDistance)){
				drive.stopDrive();
				state = States.WAIT_FOR_GEAR;
			}
		}
		if(state == States.WAIT_FOR_GEAR){
			if(gear.haveGear){
				state = States.DONE;
			}
		}
	}	
	public void toLeftPeg(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.targetingSpeed, AutoConstants.sidePegHardDistance,
				AutoConstants.leftPegHardAngle);
		visionTarget();
		if(state == States.WAIT_FOR_GEAR){
			if(gear.haveGear){
				state = States.DONE;
			}
		}
	}
	public void toRightPeg(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.targetingSpeed, AutoConstants.sidePegHardDistance,
				AutoConstants.rightPegHardAngle);
		visionTarget();
		if(state == States.WAIT_FOR_GEAR){
			if(gear.haveGear){
				state = States.DONE;
			}
		}
	}
	public void middlePegToBoilerRed(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.middlePegToBoilerRedAngle);
		visionTarget();
	}
	public void middlePegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.middlePegToBoilerBlueAngle);
		visionTarget();

	}
	public void leftPegToBoilerRed(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.leftPegToBoilerRedAngle);
		visionTarget();

	}
	public void leftPegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.leftPegToBoilerBlueAngle);
		visionTarget();

	}
	public void rightPegToBoilerRed(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.rightPegToBoilerRedAngle);
		visionTarget();
	}
	public void rightPegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.backUpSpeed, AutoConstants.backUpFromPegDistance,
				AutoConstants.rightPegToBoilerBlueAngle);
		visionTarget();

	}
	public void hopperToBoilerRed(){

	}
	public void hopperToBoilerBlue(){

	}
	public void toHopperRed(){
		//TODO
	}
	public void toHopperBlue(){
		//TODO
	}
	public void middlePegToHopperRed(){
		//TODO
	}
	public void middlePegToHopperBlue(){
		//TODO
	}
	public void leftPegToHopperRed(){
		//TODO
	}
	public void leftPegToHopperBlue(){
		//TODO
	}
	public void rightPegToHopperRed(){
		//TODO
	}
	public void rightPegToHopperBlue(){
		//TODO
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