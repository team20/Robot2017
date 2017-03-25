//Author: Sydney Walker
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
	String timeDrive = "11";
	String lowGear = "12";
	
	public String[] middleGearTimed() {
		String[] autoCode = new String[1];
		autoCode[0] = timeDrive + ";" + "0.6" + ";" + "5.0";	//speed + time
		return autoCode;
	}
	public String[] stayAtBoilerAndShoot() {
		String[] autoCode = new String[3];
		autoCode[0] = shoot + ";" + "8.0";
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-80.0" + ";" + "0.0";
		return autoCode;
	}
	public String[] hopperToBoilerRed() {
		String[] autoCode = new String[11];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "0.7";
		autoCode[1] = fastDriveStraight + ";" + "0.35" + ";" + "15.0" + ";" + "40.0";
		autoCode[2] = timeDrive + ";" + "1.0" +  ";" + "1.5";
		autoCode[3] = timeDrive + ";" + "-1.0" + ";" + "0.4";
		autoCode[4] = fastDriveStraight + ";" + "0.35" + ";" + "25.0" + ";" + "90.0";
		autoCode[5] = spinUp + ";" + "NULL";
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + "0.5";
		autoCode[7] = timeDrive + ";" + "0.0" + ";" + "0.25";
		autoCode[8] = lowGear + ";" + "NULL";
		autoCode[9] = timeDrive + ";" + "1.0" + ";" + "1.0";
		autoCode[10] = shoot + ";" + "15";
		return autoCode;
	}
	public String[] hopperToBoilerBlue() {
		String[] autoCode = new String[11];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "0.7";
		autoCode[1] = fastDriveStraight + ";" + "0.35" + ";" + "15.0" + ";" + "-40.0";
		autoCode[2] = timeDrive + ";" + "1.0" +  ";" + "1.5";
		autoCode[3] = timeDrive + ";" + "-1.0" + ";" + "0.4";
		autoCode[4] = fastDriveStraight + ";" + "0.35" + ";" + "25.0" + ";" + "-90.0";
		autoCode[5] = spinUp + ";" + "NULL";
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + "0.5";
		autoCode[7] = timeDrive + ";" + "0.0" + ";" + "0.25";
		autoCode[8] = lowGear + ";" + "NULL";
		autoCode[9] = timeDrive + ";" + "1.0" + ";" + "1.0";
		autoCode[10] = shoot + ";" + "15";
		return autoCode;
	}
	
//	public String[] hopperToBoilerBlue() {
//		String[] autoCode = new String[17];
//		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "3.0";
//		autoCode[1] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
//		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_TWO +
//				";" + AutoConstants.KPA_ANGLE_TWO_RED;
//		autoCode[3] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
////		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_THREE +
////				";" + AutoConstants.KPA_ANGLE_THREE_RED;
////		autoCode[5] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
//		autoCode[4] = wait + ";" + AutoConstants.KPA_WAIT_HOPPER;
//		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_FOUR +
//				";" + AutoConstants.KPA_ANGLE_FOUR_RED;
//		autoCode[6] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
//		autoCode[7] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_FIVE +
//				";" + AutoConstants.KPA_ANGLE_FIVE_RED;
//		autoCode[8] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
//		autoCode[9] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_SIX +
//				";" + AutoConstants.KPA_ANGLE_SIX_RED;
//		autoCode[10] = timeDrive + ";" + "1.0" + ";" + AutoConstants.KPA_STOPPING_TIME;
//		autoCode[11] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_SEVEN +
//				";" + AutoConstants.KPA_ANGLE_SEVEN_RED;
//		autoCode[12] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[13] = spinUp + ";" + "NULL";
//		autoCode[14] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_EIGHT +
//				";" + AutoConstants.KPA_ANGLE_EIGHT_RED;
//		autoCode[15] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[16] = shoot + ";" + "50";
//		return autoCode;
//	}
//	public String[] hopperToBoilerRed() {
//		String[] autoCode = new String[17];
//		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_ONE +
//				";" + AutoConstants.KPA_ANGLE_ONE_BLUE;
//		autoCode[1] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_TWO +
//				";" + AutoConstants.KPA_ANGLE_TWO_BLUE;
//		autoCode[3] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
////		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_THREE +
////				";" + AutoConstants.KPA_ANGLE_THREE_BLUE;
////		autoCode[5] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[4] = wait + ";" + AutoConstants.KPA_WAIT_HOPPER;
//		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_FOUR +
//				";" + AutoConstants.KPA_ANGLE_FOUR_BLUE;
//		autoCode[6] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[7] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_FIVE +
//				";" + AutoConstants.KPA_ANGLE_FIVE_BLUE;
//		autoCode[8] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[9] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_SIX +
//				";" + AutoConstants.KPA_ANGLE_SIX_BLUE;
//		autoCode[10] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[11] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_SEVEN +
//				";" + AutoConstants.KPA_ANGLE_SEVEN_BLUE;
//		autoCode[12] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[13] = spinUp + ";" + "NULL";
//		autoCode[14] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.KPA_DISTANCE_EIGHT +
//				";" + AutoConstants.KPA_ANGLE_EIGHT_BLUE;
//		autoCode[15] = timeDrive + ";" + "1.0" + ";" +  AutoConstants.KPA_STOPPING_TIME;
//		autoCode[16] = shoot + ";" + "50";
//		return autoCode;
//	}

	
	
	public String[] crossBaselineTimed() {
		String[] autoCode = new String[1];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "0.5";
		return autoCode;
	}
	public String[] crossBaseline() {
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.CROSS_BASELINE_DISTANCE + ";" + "0.00";
		return autoCode;
	}
	public String[] middleGear() {
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_PEG_DISTANCE + ";" + "0.00";
		return autoCode;
	}

	public String[] rightGear() {
		String[] autoCode = new String[4];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5" + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] rightGearNC() {
		String[] autoCode = new String[3];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED +  ";" + "10.0" + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.RIGHT_PEG_DISTANCE_TWO + ";" + "0.00";
		return autoCode;
	}

	public String[] leftGear() {
		String[] autoCode = new String[4];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5" + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] leftGearNC(){
		String[] autoCode = new String[3];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED +  ";" + "10.0" + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.LEFT_PEG_DISTANCE_TWO + ";" + "0.00";
		return autoCode;
	}

	public String[] boilerToClosestSideGear() {
		String[] autoCode = new String[6];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "180";
		autoCode[3] = getCameraAngle + ";" + "NULL";
		autoCode[4] = turnCameraAngle + ";" + "NULL";
		autoCode[5] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] boilerToMiddleGearRed() {
		
		
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "0.00";
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE + ";" + "0.00";
		autoCode[4] = getCameraAngle + ";" + "NULL";
		autoCode[5] = turnCameraAngle + ";" + "NULL";
		autoCode[6] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}

	public String[] boilerToMiddleGearBlue() {
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" + AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE + ";" + "0.00";
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED+ ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE + ";" + "0.00";
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
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = getCameraAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE + ";" + "0.00";
		autoCode[7] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO + ";" + "0.00";
		autoCode[8] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] middleGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		autoCode[3] = waitForGear + ";" + "NULL";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] rightGearToBoilerRed() {
		String[] autoCode = new String[8];
        autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = getCameraAngle + ";" + "NULL";
		autoCode[2] = turnCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = waitForGear + ";" + "NULL";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] leftGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE + ";" + "0.00";
		autoCode[1] = getCameraAngle + ";" + "NULL";
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = turnCameraAngle + ";" + "NULL";
		autoCode[4] = waitForGear + ";" + "NULL";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
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