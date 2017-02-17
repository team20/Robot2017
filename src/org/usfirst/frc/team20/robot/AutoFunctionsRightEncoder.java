package org.usfirst.frc.team20.robot;

public class AutoFunctionsRightEncoder {
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
	
	public AutoFunctionsRightEncoder(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT, Hopper h,
			GearMechanism g){
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
			drive.driveDistanceStraightRightEncoder(speed, hardDistance);
			if(drive.driveDistanceStraightRightEncoder(speed, hardDistance)){
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
			drive.driveDistanceStraightRightEncoder(0.8, distanceFromCamera);
			if(drive.driveDistanceStraightRightEncoder(0.8, distanceFromCamera)){
				drive.stopDrive();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_3;
			}
		}
//		if(state == States.TURN_ANGLE && group == Groups.GROUP_3){
//			drive.turnAngle(angleFromCameraTwo);
//			if(drive.turnAngle(angleFromCameraTwo)){
//				drive.stopDrive();
//				state = States.GO_DISTANCE;
//				group = Groups.GROUP_3;
//			}
//		}
//		if(state == States.GO_DISTANCE && group == Groups.GROUP_3){
//			drive.driveDistanceStraightRightEncoder(0.8, distanceFromCameraTwo);
//			if(drive.driveDistanceStraightRightEncoder(0.8, distanceFromCameraTwo)){
//				drive.stopDrive();
//				state = States.WAIT_FOR_GEAR;
//			}
//		}
	}
	public void backUpFromBoilerTurnAndTarget(){
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_BOILER_DISTANCE,
				AutoConstants.BOILER_TO_SIDE_PEG_ANGLE);
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
				if (drive.driveDistanceStraightRightEncoder(0.8, distanceFromCamera)){
					state = States.DONE;
					System.out.println("NavX Angle at End: " + drive.gyro.getAngle());
				}
			}
		}
	}
	public void crossBaseline(){
		state = States.GO_DISTANCE;
		if(state == States.GO_DISTANCE){
			drive.driveDistanceStraightRightEncoder(1, AutoConstants.CROSS_BASELINE_DISTANCE);
			if(drive.driveDistanceStraightRightEncoder(1, AutoConstants.CROSS_BASELINE_DISTANCE)){
				drive.stopDrive();
				state = States.DONE;
			}
		}
	}
	public void toMiddlePeg(){
		if(state == States.GO_DISTANCE){
			drive.driveDistanceStraightRightEncoder(0.8, AutoConstants.MIDDLE_PEG_DISTANCE);
			if(drive.driveDistanceStraightRightEncoder(0.8, AutoConstants.MIDDLE_PEG_DISTANCE)){
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
		hardDistanceHardAngle(AutoConstants.TARGETING_SPEED, AutoConstants.SIDE_PEG_HARD_DISTANCE,
				AutoConstants.LEFT_PEG_HARD_ANGLE);
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
		hardDistanceHardAngle(AutoConstants.TARGETING_SPEED, AutoConstants.SIDE_PEG_HARD_DISTANCE,
				AutoConstants.RIGHT_PEG_HARD_ANGLE);
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
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_MIDDLE_PEG_TO_BOILER_ANGLE);
		visionTarget();
	}
	public void middlePegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_MIDDLE_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}
	public void leftPegToBoilerRed(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_LEFT_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}
	public void leftPegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_LEFT_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}
	public void rightPegToBoilerRed(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_RIGHT_PEG_TO_BOILER_ANGLE);
		visionTarget();
	}
	public void rightPegToBoilerBlue(){
		state = States.GO_DISTANCE;		
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_RIGHT_PEG_TO_BOILER_ANGLE);
		visionTarget();
		state = States.DONE;
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