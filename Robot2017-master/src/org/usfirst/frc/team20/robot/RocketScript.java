package org.usfirst.frc.team20.robot;

public class RocketScript {
	String getCameraAngle = "3";  //(angle, ip, port) //gets distance too
	String shoot = "6";
	String stopShooting = "7";
	String waitForGear = "8";
	String rocketTurn = "9";
	String fastDriveStraight = "10";
	String turnCameraAngle = "11";
	String goCameraDistance = "12";
	
    
	public String[] barraAuto(){
		String[] autoCode = new String[3];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	public String[] testCode() {
		String[] autoCode = new String[2];
		autoCode[0] = fastDriveStraight + ";"  + "50.00"   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + "-60.00";
		return autoCode;
	}
	//done
	public String[] gearToPeg(){
		String[] autoCode = new String[3];
		autoCode[0] = fastDriveStraight + ";" + "98.5"   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + "-60.00";
		autoCode[2] = fastDriveStraight + ";" + "12.00"   + ";" + "0.00";
		return autoCode;
		
	}
	
	public String[] rawDriveTurnLeft(){
		String[] autoCode = new String[5];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.CROSS_BASELINE_DISTANCE   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = turnCameraAngle + ";" + "NULL";
		autoCode[4] = goCameraDistance + ";" + "NULL";
		
		return autoCode;
	}
	
	public String[] crossBaseline(){
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.CROSS_BASELINE_DISTANCE   + ";" + "0.00";
		return autoCode;
	}
	
	public String[] stayAtBoilerAndShoot(){
		String[] autoCode = new String[1];
		autoCode[0] = shoot + ";" + 0;
		return autoCode;
	}
	public String[] middleGear(){
		String[] autoCode = new String[3];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	
	public String[] middleGearNC(){
		String[] autoCode = new String[1];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_DISTANCE   + ";" + "0.00";
		return autoCode;
	}
	
	public String[] rightGear(){
		String[] autoCode = new String[4];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE   + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + "5" + ";" +  AutoConstants.RIGHT_PEG_HARD_ANGLE;
//		autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
//		autoCode[3] = turnCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";	
		return autoCode;
	}
	
	public String[] rightGearNC(){
		String[] autoCode = new String[2];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE   + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + "68" + ";" +  AutoConstants.RIGHT_PEG_HARD_ANGLE;
		//autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		//autoCode[2] = fastDriveStraight + ";" + "72" + ";" + "0.00"; 
		return autoCode;
	}
	
	
	public String[] leftGear(){
		String[] autoCode = new String[5];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE  + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = turnCameraAngle + ";" + "NULL";
		autoCode[4] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToClosestSideGear(){
		String[] autoCode = new String[7];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE  + ";" + "0.00";
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_SIDE_PEG_HARD_ANGLE;
		autoCode[4] = getCameraAngle + ";" + "NULL";
		autoCode[5] = turnCameraAngle + ";" + "NULL";
		autoCode[6] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearRed(){
		String[] autoCode = new String[8];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE  + ";" + "0.00";
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_RED;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE  + ";" + "0.00";
		autoCode[5] = getCameraAngle + ";" + "NULL";
		autoCode[6] = turnCameraAngle + ";" + "NULL";
		autoCode[7] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearBlue(){
		String[] autoCode = new String[8];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE  + ";" + "0.00";
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_BLUE;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE  + ";" + "0.00";
		autoCode[5] = getCameraAngle + ";" + "NULL";
		autoCode[6] = turnCameraAngle + ";" + "NULL";
		autoCode[7] = goCameraDistance + ";" + "NULL";
		return autoCode;
	}
	
	public String[] middleGearToBoilerRed(){
		String[] autoCode = new String[10];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		autoCode[3] = waitForGear + ";" + "NULL";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE  + ";" + "0.00";
		autoCode[5] = getCameraAngle + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_TWO;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO   + ";" + "0.00";
		autoCode[9] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] middleGearToBoilerBlue(){
		String[] autoCode = new String[10];
		autoCode[0] = getCameraAngle + ";" + "NULL";
		autoCode[1] = turnCameraAngle + ";" + "NULL";
		autoCode[2] = goCameraDistance + ";" + "NULL";
		autoCode[3] = waitForGear + ";" + "NULL";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE   + ";" + "0.00";
		autoCode[5] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_ONE;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_TWO;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO  + ";" + "0.00";
		autoCode[9] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] rightGearToBoilerRed(){
		String[] autoCode = new String[10];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.RIGHT_PEG_HARD_DISTANCE   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] =  turnCameraAngle + ";" + "NULL";
		autoCode[4] = goCameraDistance + ";" + "NULL";
		autoCode[5] = waitForGear + ";" + "NULL";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE   + ";" + "0.00";
		autoCode[9] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] leftGearToBoilerBlue(){
		String[] autoCode = new String[10];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.LEFT_PEG_HARD_DISTANCE   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = getCameraAngle + ";" + "NULL";
		autoCode[4] = turnCameraAngle + ";" + "NULL";
		autoCode[5] = waitForGear + ";" + "NULL";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE   + ";" + "0.00";
		autoCode[9] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] hopperToBoilerRed(){
		String[] autoCode = new String[13];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_1_RED   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_1_RED;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_2_RED   + ";" + "0.00";
		autoCode[3] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_2_RED;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_3_RED   + ";" + "0.00";
		autoCode[5] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_3_RED;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_4_RED   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_4_RED;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_5_RED   + ";" + "0.00";
		autoCode[9] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_5_RED;
		autoCode[10] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_6_RED   + ";" + "0.00";
		autoCode[11] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_5_RED;
		autoCode[12] = shoot + ";" + 0;
		return autoCode;
	}
	public String[] hopperToBoilerBlue(){
		String[] autoCode = new String[13];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_1_BLUE   + ";" + "0.00";
		autoCode[1] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_1_BLUE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_2_BLUE   + ";" + "0.00";
		autoCode[3] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_2_BLUE;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_3_BLUE   + ";" + "0.00";
		autoCode[5] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_3_BLUE;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_4_BLUE   + ";" + "0.00";
		autoCode[7] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_4_BLUE;
		autoCode[8] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_5_BLUE   + ";" + "0.00";
		autoCode[9] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_5_BLUE;
		autoCode[10] = fastDriveStraight + ";" + AutoConstants.KPA40_DISTANCE_6_BLUE   + ";" + "0.00";
		autoCode[11] = rocketTurn + ";" + AutoConstants.KPA40_ANGLE_5_BLUE   + ";" + "0.00";
		autoCode[12] = shoot + ";" + 0;
		return autoCode;
	}
}