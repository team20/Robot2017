package org.usfirst.frc.team20.robot;

public class AutoFunctionsRightEncoder {
	DriveTrain drive;
	FlyWheel flywheel;
	VisionTargeting vision;
	GroundCollector collector;
	FuelTank tank;
	GearMechanism gear;
	Util util;
	int state;
	int group;
	double distanceFromCamera = 0.0;
	double angleFromCamera = 0.0;

	public AutoFunctionsRightEncoder(DriveTrain d, FlyWheel f, GroundCollector c, VisionTargeting vT, FuelTank t, GearMechanism g) {
		drive = d;
		flywheel = f;
		vision = vT;
		collector = c;
		tank = t;
		gear = g;
		util = new Util();
//		state = States.GO_DISTANCE;
//		group = Groups.GROUP_1;
		state = States.TURN_ANGLE;
		group = Groups.GROUP_1;
	}

	private void runBeforeTargeting() {
		String getSocketData;
		drive.gyro.reset();
		// Distance From Camera
		getSocketData = util.getCameraAngle();
		String[] values = getSocketData.split("\\*");
		distanceFromCamera = Double.parseDouble(values[0]);
		angleFromCamera = Double.parseDouble(values[1]);
//		drive.turnController.setSetpoint(angleFromCamera);
//		drive.turnController.enable();
	}

	private boolean hardDistanceHardAngle(double speed, double hardDistance, double hardAngle) {
		if (state == States.GO_DISTANCE && group == Groups.GROUP_1) {
			System.out.println("*******************************************Drive Straight 1");
			if (drive.driveDistanceStraightRightEncoder(speed, hardDistance)) {
				System.out.println("*******************************************Done Driving");
				drive.turnDrive(0.0,0.0);
				state = States.TURN_ANGLE;
				group = Groups.GROUP_1;
			}
		}
		if (state == States.TURN_ANGLE && group == Groups.GROUP_1) {
			System.out.println("*******************************************Turn Angle 1");
			if (drive.turnAngle(hardAngle)) {
				drive.stopDrive();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_2;
				return true;
			}
		}
		return false;
	}

	private void visionTarget() {
		runBeforeTargeting();
		if (state == States.TURN_ANGLE && group == Groups.GROUP_2) {
			System.out.println("*******************************************Drive Straight 2");
			if (drive.turnAngle(angleFromCamera)) {
				drive.stopDrive();
				state = States.GO_DISTANCE;
				group = Groups.GROUP_2;
			}
		}
		if (state == States.GO_DISTANCE && group == Groups.GROUP_2) {
			System.out.println("*******************************************Turn Angle 2");
			if (drive.driveDistanceStraightRightEncoder(0.8, distanceFromCamera)) {
				drive.stopDrive();
				state = States.TURN_ANGLE;
				group = Groups.GROUP_3;
			}
		}
	}

	public void backUpFromBoilerTurnAndTarget() {
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_BOILER_DISTANCE,
				AutoConstants.BOILER_TO_SIDE_PEG_ANGLE);
		if (state == States.TURN_ANGLE && group == Groups.GROUP_2) {
			visionTarget();
		}
		if (state == States.WAIT_FOR_GEAR) {
			if (gear.checkGear()) {
				state = States.DONE;
			}
		}

	}

	public void autoMode1() {
		runBeforeTargeting();
		state = States.TURN_ANGLE;
		if (state == States.TURN_ANGLE) {
			System.out.println("**********************************angleFromCamera = " + angleFromCamera);
			System.out.println("**********************************Navx = " + drive.gyro.getAngle());
			if (drive.turnAngle(angleFromCamera)) {
				state = States.GO_DISTANCE;
				// set encoder to zero so it can calculate distance
				drive.masterLeft.setEncPosition(0);
			}
			if (state == States.GO_DISTANCE) {
				System.out.println("*******lkjljlkjlkj;jl;dj;kafjf;ad************Go distance parameters");
				if (drive.driveDistanceStraightRightEncoder(0.8, distanceFromCamera)) {
					state = States.DONE;
					System.out.println("NavX Angle at End: " + drive.gyro.getAngle());
				}
			}
		}
	}

	public void crossBaseline() {
		state = States.GO_DISTANCE;
		if (state == States.GO_DISTANCE) {
			drive.driveDistanceStraightRightEncoder(1, AutoConstants.CROSS_BASELINE_DISTANCE);
			if (drive.driveDistanceStraightRightEncoder(1, AutoConstants.CROSS_BASELINE_DISTANCE)) {
				drive.stopDrive();
				state = States.DONE;
			}
		}
	}

	public void toMiddlePeg() {
		if (state == States.GO_DISTANCE) {
			drive.driveDistanceStraightRightEncoder(0.8, AutoConstants.MIDDLE_PEG_DISTANCE);
			if (drive.driveDistanceStraightRightEncoder(0.8, AutoConstants.MIDDLE_PEG_DISTANCE)) {
				drive.stopDrive();
				state = States.WAIT_FOR_GEAR;
			}
		}
		if (state == States.WAIT_FOR_GEAR) {
			if (gear.checkGear()) {
				state = States.DONE;
			}
		}
	}

	public void toLeftPeg() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.TARGETING_SPEED, AutoConstants.SIDE_PEG_HARD_DISTANCE,
				AutoConstants.LEFT_PEG_HARD_ANGLE);
		visionTarget();
		if (state == States.WAIT_FOR_GEAR) {
			if (gear.checkGear()) {
				state = States.DONE;
			}
		}
	}

	public void toRightPeg() {
		if(hardDistanceHardAngle(AutoConstants.TARGETING_SPEED, AutoConstants.SIDE_PEG_HARD_DISTANCE,
				AutoConstants.RIGHT_PEG_HARD_ANGLE)){
			visionTarget();			
		}
//		if (state == States.WAIT_FOR_GEAR) {
//			if (gear.haveGear) {
//				state = States.DONE;
//			}
//		}
	}

	public void middlePegToBoilerRed() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_MIDDLE_PEG_TO_BOILER_ANGLE);
		visionTarget();
	}

	public void middlePegToBoilerBlue() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_MIDDLE_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}

	public void leftPegToBoilerRed() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_LEFT_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}

	public void leftPegToBoilerBlue() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_LEFT_PEG_TO_BOILER_ANGLE);
		visionTarget();

	}

	public void rightPegToBoilerRed() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.RED_RIGHT_PEG_TO_BOILER_ANGLE);
		visionTarget();
	}

	public void rightPegToBoilerBlue() {
		state = States.GO_DISTANCE;
		group = Groups.GROUP_1;
		hardDistanceHardAngle(AutoConstants.BACK_UP_SPEED, AutoConstants.BACK_UP_FROM_PEG_DISTANCE,
				AutoConstants.BLUE_RIGHT_PEG_TO_BOILER_ANGLE);
		visionTarget();
		state = States.DONE;
	}

	public void HopperToBoilerRed() {

	}

	public void HopperToBoilerBlue() {

	}

	public void toHopperRed() {
		// TODO
	}

	public void toHopperBlue() {
		// TODO
	}

	public void middlePegToHopperRed() {
		// TODO
	}

	public void middlePegToHopperBlue() {
		// TODO
	}

	public void leftPegToHopperRed() {
		// TODO
	}

	public void leftPegToHopperBlue() {
		// TODO
	}

	public void rightPegToHopperRed() {
		// TODO
	}

	public void rightPegToHopperBlue() {
		// TODO
	}

	public void shoot(double RPMS) {
		boolean shooting = true;
		flywheel.shootWithEncoders(RPMS);
		collector.intake(1);
		tank.tankMotorIntoFlywheel(1);
		if (shooting) {
			tank.runAgitator();
		}
	}

	public void stopFlywheel() {
		flywheel.stopFlywheel();
	}
}