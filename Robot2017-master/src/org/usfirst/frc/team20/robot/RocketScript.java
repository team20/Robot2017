package org.usfirst.frc.team20.robot;

public class RocketScript {
	String visionTarget = "1";
	String rocketTurn = "2";	
	String driveDistance = "3";
	String shoot = "4";
	String stopShooting = "5";
    String arcTurnRight = "6";
    String arcTurnLeft = "7";
	
	public String[] crossBaseline(){
		String[] autoCode = new String[1];
		autoCode[0] = driveDistance + ";" + AutoConstants.CROSS_BASELINE_DISTANCE;
		return autoCode;
	}
	public String[] stayAtBoilerAndShoot(){
		String[] autoCode = new String[1];
		autoCode[0] = shoot + ";" + 0;
		return autoCode;
	}
	public String[] middleGear(){
		String[] autoCode = new String[1];
		autoCode[0] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] rightGear(){
		String[] autoCode = new String[3];
		autoCode[0] = driveDistance + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] leftGear(){
		String[] autoCode = new String[3];
		autoCode[0] = driveDistance + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rocketTurn + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToClosestSideGear(){
		String[] autoCode = new String[5];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_SIDE_PEG_HARD_ANGLE;
		autoCode[4] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearRed(){
		String[] autoCode = new String[6];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_RED;
		autoCode[4] = driveDistance + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE;
		autoCode[5] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] boilerToMiddleGearBlue(){
		String[] autoCode = new String[6];
		autoCode[0] = shoot + ";" +  AutoConstants.FLYWHEEL_DELAY_START_BOILER;
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_BOILER_DISTANCE;
		autoCode[3] = rocketTurn + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_HARD_ANGLE_BLUE;
		autoCode[4] = driveDistance + ";" + AutoConstants.BOILER_TO_MIDDLE_PEG_DISTANCE;
		autoCode[5] = visionTarget + ";" + "NULL";
		return autoCode;
	}
	public String[] middleGearToBoilerRed(){
		String[] autoCode = new String[7];
		autoCode[0] = visionTarget + ";" + "NULL";
		autoCode[1] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[2] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE;
		autoCode[3] = driveDistance + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE;
		autoCode[4] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_TWO;
		autoCode[5] = driveDistance + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO;
		autoCode[6] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] middleGearToBoilerBlue(){
		String[] autoCode = new String[7];
		autoCode[0] = visionTarget + ";" + "NULL";
		autoCode[1] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[2] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_ONE;
		autoCode[3] = driveDistance + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_ONE;
		autoCode[4] = rocketTurn + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_TWO;
		autoCode[5] = driveDistance + ";" + AutoConstants.MIDDLE_PEG_TO_BOILER_DISTANCE_TWO;
		autoCode[6] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] rightGearToBoilerRed(){
		String[] autoCode = new String[9];
		autoCode[0] = driveDistance + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rocketTurn + ";" + AutoConstants.RIGHT_PEG_HARD_ANGLE;
		autoCode[2] = visionTarget + ";" + "NULL";
		autoCode[5] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[6] = rocketTurn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[7] = driveDistance + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE;
		autoCode[8] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] leftGearToBoilerBlue(){
		String[] autoCode = new String[9];
		autoCode[0] = driveDistance + ";" + AutoConstants.SIDE_PEG_HARD_DISTANCE;
		autoCode[1] = rocketTurn + ";" + AutoConstants.LEFT_PEG_HARD_ANGLE;
		autoCode[2] = visionTarget + ";" + "NULL";
		autoCode[5] = driveDistance + ";" + AutoConstants.BACK_UP_FROM_PEG_DISTANCE;
		autoCode[6] = rocketTurn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE;
		autoCode[7] = driveDistance + ";" + AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE;
		autoCode[8] = shoot + ";" + "0"; 
		return autoCode;
	}
	public String[] hopperToBoilerRed(){
		String[] autoCode = new String[5];
		autoCode[0] = arcTurnRight + ";" + AutoConstants.ARC_TURN_ONE_LONG_RED + ";" + AutoConstants.ARC_TURN_ONE_SHORT_RED;
		autoCode[1] = arcTurnLeft + ";" + AutoConstants.ARC_TURN_TWO_LONG_RED + ";" + AutoConstants.ARC_TURN_TWO_SHORT_RED;
		autoCode[2] = rocketTurn + ";" + AutoConstants.KPA_HARD_ANGLE_RED;
		autoCode[3] = rocketTurn + ";" + AutoConstants.KPA_HARD_DISTANCE_RED;		
		autoCode[4] = shoot + ";" + "0";
		return autoCode;
	}
	public String[] hopperToBoilerBlue(){
		String[] autoCode = new String[5];
		autoCode[0] = arcTurnRight + ";" + AutoConstants.ARC_TURN_ONE_LONG_BLUE + ";" + AutoConstants.ARC_TURN_ONE_SHORT_BLUE;
		autoCode[1] = arcTurnLeft + ";" + AutoConstants.ARC_TURN_TWO_LONG_BLUE + ";" + AutoConstants.ARC_TURN_TWO_SHORT_BLUE;
		autoCode[2] = rocketTurn + ";" + AutoConstants.KPA_HARD_ANGLE_BLUE;
		autoCode[3] = rocketTurn + ";" + AutoConstants.KPA_HARD_DISTANCE_BLUE;		
		autoCode[4] = shoot + ";" + "0";
		return autoCode;
	}
}