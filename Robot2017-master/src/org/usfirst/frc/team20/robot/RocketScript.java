package org.usfirst.frc.team20.robot;

public class RocketScript {
	String getCameraAngle = "1"; // (angle, ip, port) //gets distance too
	String shoot = "2";
	String stopShooting = "3";
	String waitForGear = "4";
	String fastDriveStraight = "5";
	String turnCameraAngle = "6";
	String goCameraDistance = "7";
	String arcTurn = "8";
	String wait = "9";
	String spinUp = "10";

	public String[] crossBaseline() {
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.CROSS_BASELINE_DISTANCE + ";" + "0.00";
		return autoCode;
	}

	public String[] stayAtBoilerAndShoot() {
		String[] autoCode = new String[1];
		autoCode[0] = shoot + ";" + "14";
		return autoCode;
	}

	public String[] middleGearNC() {
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_DISTANCE + ";" + "0.00";
		return autoCode;
	}

	public String[] rightGear() {
		String[] autoCode = new String[4];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + "5" + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] rightGearNC() {
		String[] autoCode = new String[2];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_DISTANCE_TWO + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		return autoCode;
	}

	public String[] leftGear() {
		String[] autoCode = new String[4];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + "10.0" + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] leftGearNC(){
		String[] autoCode = new String[2];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_DISTANCE_TWO + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		return autoCode;		
	}

	public String[] boilerToClosestSideGear() {
		String[] autoCode = new String[3];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "180";
//		autoCode[3] = getCameraAngle + ";" + "NULL";
//		autoCode[4] = turnCameraAngle + ";" + "NULL";
//		autoCode[5] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] boilerToMiddleGearRed() {
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "0.00";
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE + ";" + "0.00";
		autoCode[4] = getCameraAngle + ";" + "NULL";
		autoCode[5] = turnCameraAngle + ";" + "NULL";
		autoCode[6] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] boilerToMiddleGearBlue() {
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "0.00";
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE + ";" + "0.00";
		autoCode[4] = getCameraAngle + ";" + "NULL";
		autoCode[5] = turnCameraAngle + ";" + "NULL";
		autoCode[6] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] middleGearToBoilerRed() {
		String[] autoCode = new String[9];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		autoCode[3] = waitForGear + ";" + "NULL";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = getCameraAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE + ";" + "0.00";
		autoCode[7] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO + ";" + "0.00";
		autoCode[8] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] middleGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		autoCode[3] = waitForGear + ";" + "NULL";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] rightGearToBoilerRed() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = getCameraAngle + ";" + "NULL";
		autoCode[2] = turnCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = waitForGear + ";" + "NULL";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] leftGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = getCameraAngle + ";" + "NULL";
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = turnCameraAngle + ";" + "NULL";
		autoCode[4] = waitForGear + ";" + "NULL";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] hopperToBoilerRed() {
		String[] autoCode = new String[11];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_ONE_RED + ";" + AutoConstants.KPA_ANGLE_ONE_RED;
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_TWO_RED + ";" + AutoConstants.KPA_ANGLE_TWO_RED;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_THREE_RED + ";" + AutoConstants.KPA_ANGLE_THREE_RED;
		autoCode[3] = wait + ";" + AutoConstants.KPA_WAIT_HOPPER;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_FOUR_RED + ";" + AutoConstants.KPA_ANGLE_FOUR_RED;
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_FIVE_RED + ";" + AutoConstants.KPA_ANGLE_FIVE_RED;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_SIX_RED + ";" + AutoConstants.KPA_ANGLE_SIX_RED;
		autoCode[7] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_SEVEN_RED + ";" + AutoConstants.KPA_ANGLE_SEVEN_RED;
		autoCode[8] = spinUp + ";" + "NULL";
		autoCode[9] = fastDriveStraight + ";" + AutoConstants.KPA_DISTANCE_EIGHT_RED + ";" + AutoConstants.KPA_ANGLE_EIGHT_RED;
		autoCode[10] = shoot + ";" + "50";
		return autoCode;
	}

	public String[] hopperToBoilerBlue() {
		String[] autoCode = new String[4];
		return autoCode;
	}
//	public String[] hopperToBoilerRed() {
//		String[] autoCode = new String[4];
//		autoCode[0] = arcTurn + ";" + AutoConstants.ARC_TURN_ONE_LONG_RED + ";" + AutoConstants.ARC_TURN_ONE_SHORT_RED + ";" + "false";
//		autoCode[1] = arcTurn + ";" + AutoConstants.ARC_TURN_TWO_LONG_RED + ";" + AutoConstants.ARC_TURN_TWO_SHORT_RED + ";" + "true";
//		autoCode[2] = fastDriveStraight + ";" + AutoConstants.KPA_HARD_DISTANCE_RED + ";" + AutoConstants.KPA_HARD_ANGLE_RED; 
//		autoCode[3] = shoot + ";" + "5";
//		return autoCode;
//	}
//
//	public String[] hopperToBoilerBlue() {
//		String[] autoCode = new String[4];
//		autoCode[0] = arcTurn + ";" + AutoConstants.ARC_TURN_ONE_LONG_BLUE + ";" + AutoConstants.ARC_TURN_ONE_SHORT_BLUE + ";" + "true";
//		autoCode[1] = arcTurn + ";" + AutoConstants.ARC_TURN_TWO_LONG_BLUE + ";" + AutoConstants.ARC_TURN_TWO_SHORT_BLUE + ";" + "false";
//		autoCode[2] = fastDriveStraight + ";" + AutoConstants.KPA_HARD_DISTANCE_BLUE + ";" + AutoConstants.KPA_HARD_ANGLE_BLUE; 
//		autoCode[3] = shoot + ";" + "5";
//		return autoCode;
//	}
}