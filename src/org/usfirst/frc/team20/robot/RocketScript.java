package org.usfirst.frc.team20.robot;

public class RocketScript {
	String smartDriveStraight = "1";  
	String smartTurnAngle = "2";	
	String getCameraAngle = "3";  //(angle, ip, port) //gets distance too
	String rawTurnAngle = "4";  //angle
	String rawDriveStraight = "5"; // ( distance, fudge)
	String shoot = "6";
	String stopShooting = "7";
    
	public String[] crossBaseline(){
		String[] autoCode = new String[1];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.CROSS_BASELINE_DISTANCE;
		return autoCode;
	}
	public String[] stayAtBoilerAndShoot(){
		String[] autoCode = new String[1];
		autoCode[0] = shoot + ";" + 0;
		return autoCode;
	}
	public String[] middleGear(){
		String[] autoCode = new String[2];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = smartTurnAngle + ";" + "NULL";
		autoCode[2] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] rightGear(){
		String[] autoCode = new String[5];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = smartTurnAngle + ";" + "NULL";
		autoCode[4] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] leftGear(){
		String[] autoCode = new String[5];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = smartTurnAngle + ";" + "NULL";
		autoCode[4] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToClosestSideGear(){	//TODO check field drawings to see which side
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rawTurnAngle + ";" + AutoConstants.BOILER_TO_SIDE_PEG_HARD_ANGLE;
		autoCode[4] = getCameraAngle + ";" + "NULL";
		autoCode[5] = smartTurnAngle + ";" + "NULL";
		autoCode[6] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearRed(){
		String[] autoCode = new String[8];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rawTurnAngle + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_RED;
		autoCode[4] = rawDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE;
		autoCode[5] = getCameraAngle + ";" + "NULL";
		autoCode[6] = smartTurnAngle + ";" + "NULL";
		autoCode[7] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearBlue(){
		String[] autoCode = new String[8];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rawTurnAngle + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_BLUE;
		autoCode[4] = rawDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE;
		autoCode[5] = getCameraAngle + ";" + "NULL";
		autoCode[6] = smartTurnAngle + ";" + "NULL";
		autoCode[7] = smartDriveStraight + ";" + "NULL";
		return autoCode;
	}
	public String[] middleGearToBoilerRed(){
		String[] autoCode = new String[10];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = smartTurnAngle + ";" + "NULL";
		autoCode[2] = smartDriveStraight + ";" + "NULL";
		autoCode[3] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[4] = rawTurnAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE;
		autoCode[5] = rawDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE;
		autoCode[6] = rawTurnAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_TWO;
		autoCode[7] = rawDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO;
		autoCode[8] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] middleGearToBoilerBlue(){
		String[] autoCode = new String[10];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = smartTurnAngle + ";" + "NULL";
		autoCode[2] = smartDriveStraight + ";" + "NULL";
		autoCode[3] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[4] = rawTurnAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_ONE;
		autoCode[5] = rawDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE;
		autoCode[6] = rawTurnAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_TWO;
		autoCode[7] = rawDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO;
		autoCode[8] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] rightGearToBoilerRed(){
		String[] autoCode = new String[9];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = smartTurnAngle + ";" + "NULL";
		autoCode[4] = smartDriveStraight + ";" + "NULL";
		autoCode[5] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[6] = rawTurnAngle + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[7] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE;
		autoCode[8] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] leftGearToBoilerBlue(){
		String[] autoCode = new String[9];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = smartTurnAngle + ";" + "NULL";
		autoCode[4] = smartDriveStraight + ";" + "NULL";
		autoCode[5] = rawDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[6] = rawTurnAngle + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[7] = rawDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE;
		autoCode[8] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] hopperToBoilerRed(){
		String[] autoCode = new String[13];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_1_RED;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_1_RED;
		autoCode[2] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_2_RED;
		autoCode[3] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_2_RED;
		autoCode[4] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_3_RED;
		autoCode[5] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_3_RED;
		autoCode[6] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_4_RED;
		autoCode[7] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_4_RED;
		autoCode[8] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_5_RED;
		autoCode[9] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_5_RED;
		autoCode[10] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_6_RED;
		autoCode[11] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_5_RED;
		autoCode[12] = shoot + ";" + 0;
		return autoCode;
	}
	public String[] hopperToBoilerBlue(){
		String[] autoCode = new String[13];
		autoCode[0] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_1_BLUE;
		autoCode[1] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_1_BLUE;
		autoCode[2] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_2_BLUE;
		autoCode[3] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_2_BLUE;
		autoCode[4] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_3_BLUE;
		autoCode[5] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_3_BLUE;
		autoCode[6] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_4_BLUE;
		autoCode[7] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_4_BLUE;
		autoCode[8] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_5_BLUE;
		autoCode[9] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_5_BLUE;
		autoCode[10] = rawDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_6_BLUE;
		autoCode[11] = rawTurnAngle + ";" + AutoConstants.KPA40_ANGLE_5_BLUE;
		autoCode[12] = shoot + ";" + 0;
		return autoCode;
	}
}