//Author: Sydney Walker
package org.usfirst.frc.team20.robot;

public class RocketScript {
	String turn = "1";
	String arcTurn = "2"; // speed, radius of circle, turn angle, right?
	String fastDriveStraight = "3";
	String timeDrive = "4";
	String shoot = "5";
	String stopShooting = "6";
	String wait = "7";
	String spinUp = "8";
	String lowGear = "9";

	public String[] middleGear() {
		String[] autoCode = new String[1];
		autoCode[0] = timeDrive + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_GEAR_TIME;
		return autoCode;
	}

	public String[] middleGearNeutralZoneRed() {
		String[] autoCode = new String[7];
		autoCode[0] = timeDrive + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_GEAR_TIME;
		autoCode[1] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_LONG;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.PEG_BACK_AWAY_PEG + ";" + "0.0";
		autoCode[3] = turn + ";" + "-90.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.AROUND_AIRSHIP + ";" + "0.0";
		autoCode[5] = turn + ";" + "90.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.NEUTRAL_ZONE_DISTANCE + ";" + "0.0";
		return autoCode;
	}

	public String[] middleGearNeutralZoneBlue() {
		String[] autoCode = new String[7];
		autoCode[0] = timeDrive + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_GEAR_TIME;
		autoCode[1] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_LONG;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.PEG_BACK_AWAY_PEG + ";" + "0.0";
		autoCode[3] = turn + ";" + "90.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.AROUND_AIRSHIP + ";" + "0.0";
		autoCode[5] = turn + ";" + "-90.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.NEUTRAL_ZONE_DISTANCE + ";" + "0.0";
		return autoCode;
	}

	public String[] rightGear() {
		String[] autoCode = new String[3];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		return autoCode;
	}

	public String[] rightGearNeutralZone() {
		String[] autoCode = new String[7];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_LONG;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.PEG_BACK_AWAY_PEG + ";" + "0.0";
		autoCode[5] = turn + ";" + "60.0";
		autoCode[6] = fastDriveStraight + ";" + "1.0" + ";" + AutoConstants.NEUTRAL_ZONE_DISTANCE + ";" + "0.0";
		return autoCode;
	}
	
	public String[] leftGear() {
		String[] autoCode = new String[3];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		return autoCode;
	}
	
	public String[] leftGearNeutralZone() {
		String[] autoCode = new String[7];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_LONG;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.PEG_BACK_AWAY_PEG + ";" + "0.0";
		autoCode[5] = turn + ";" + "-60.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.NEUTRAL_ZONE_DISTANCE + ";" + "0.0";
		return autoCode;
	}


	public String[] stayAtBoilerAndShoot() {
		String[] autoCode = new String[3];
		autoCode[0] = shoot + ";" + "8.0";
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-80.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] rightGearToBoilerRed() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_SHORT;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.BOILER_BACK_AWAY_PEG + ";" + "0.00";
		autoCode[5] = turn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE_RED;
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SIDE_BOILER_TIME;
		autoCode[7] = shoot + ";" + "15";
		return autoCode;
	}

	public String[] leftGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = turn + ";" + AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_SHORT;
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.BOILER_BACK_AWAY_PEG + ";" + "0.00";
		autoCode[5] = turn + ";" + AutoConstants.SIDE_PEG_TO_BOILER_ANGLE_RED;
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SIDE_BOILER_TIME;
		autoCode[7] = shoot + ";" + "15";
		return autoCode;
	}

	public String[] middleGearToBoilerRed() {
		String[] autoCode = new String[8];
		autoCode[0] = timeDrive + ";" + AutoConstants.MIDDLE_BOILER_FAST_SPEED + ";" + AutoConstants.MIDDLE_BOILER_FAST_TIME;
		autoCode[1] = timeDrive + ";" + AutoConstants.MIDDLE_BOILER_SLOW_SPEED + ";" + AutoConstants.MIDDLE_BOILER_SLOW_TIME;
		autoCode[2] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_SHORT;
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_BOILER_BACK_UP + ";" + "0.0";
		autoCode[4] = turn + ";" + AutoConstants.MIDDLE_BOILER_ANGLE_RED;
		autoCode[5] = spinUp + ";" + "NULL";
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + AutoConstants.MIDDLE_BOILER_FINAL_DRIVE;
		autoCode[7] = shoot + ";" + "15.0";
		return autoCode;
	}

	public String[] middleGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = timeDrive + ";" + AutoConstants.MIDDLE_BOILER_FAST_SPEED + ";" + AutoConstants.MIDDLE_BOILER_FAST_TIME;
		autoCode[1] = timeDrive + ";" + AutoConstants.MIDDLE_BOILER_SLOW_SPEED + ";" + AutoConstants.MIDDLE_BOILER_SLOW_TIME;
		autoCode[2] = wait + ";" + AutoConstants.GEAR_WAIT_TIME_SHORT;
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + AutoConstants.MIDDLE_BOILER_BACK_UP + ";" + "0.0";
		autoCode[4] = turn + ";" + AutoConstants.MIDDLE_BOILER_ANGLE_BLUE;
		autoCode[5] = spinUp + ";" + "NULL";
		autoCode[6] = timeDrive + ";" + "1.0" + ";" + AutoConstants.MIDDLE_BOILER_FINAL_DRIVE;
		autoCode[7] = shoot + ";" + "15.0";
		return autoCode;
	}

	public String[] hopperToBoilerRed() {
		String[] autoCode = new String[12];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_ONE;
		autoCode[1] = timeDrive + ";" + "-0.5" + ";" + "0.2";	//FASTER WAY TO STOP
		autoCode[2] = timeDrive + ";" + "0.80" + ";" + AutoConstants.SLAM_DRIVE_TWO;
		autoCode[3] = wait + ";" + AutoConstants.SLAM_HOPPER_WAIT;
		autoCode[4] = timeDrive + ";" + "-1.0" + ";" + AutoConstants.SLAM_BACK_UP;
		autoCode[5] = turn + ";" + AutoConstants.SLAM_ANGLE_RED;
		autoCode[6] = spinUp + ";" + "NULL";
		autoCode[7] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_THREE;
		autoCode[8] = lowGear + ";" + "NULL";
		autoCode[9] = shoot + ";" + "0.0";	
		autoCode[10] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_FOUR;
		autoCode[11] = shoot + ";" + "15";	
		return autoCode;
	}

	public String[] hopperToBoilerBlue() {
		String[] autoCode = new String[12];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_ONE;
		autoCode[1] = timeDrive + ";" + "-0.5" + ";" + "0.2";	//FASTER WAY TO STOP
		autoCode[2] = timeDrive + ";" + "0.80" + ";" + AutoConstants.SLAM_DRIVE_TWO;
		autoCode[3] = wait + ";" + AutoConstants.SLAM_HOPPER_WAIT;
		autoCode[4] = timeDrive + ";" + "-1.0" + ";" + AutoConstants.SLAM_BACK_UP;
		autoCode[5] = turn + ";" + AutoConstants.SLAM_ANGLE_BLUE;
		autoCode[6] = spinUp + ";" + "NULL";
		autoCode[7] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_THREE;
		autoCode[8] = lowGear + ";" + "NULL";
		autoCode[9] = shoot + ";" + "0.0";	
		autoCode[10] = timeDrive + ";" + "1.0" + ";" + AutoConstants.SLAM_DRIVE_FOUR;
		autoCode[11] = shoot + ";" + "15";	
		return autoCode;
	}
	
	//FOR TESTING PURPOSES
	public String[] testTurningLeft60() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "-60.0";
		return autoCode;
	}

	public String[] testTurningRight60() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "60.0";
		return autoCode;
	}

	public String[] testTurningLeft90() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "-90.0";
		return autoCode;
	}

	public String[] testTurningRight90() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "90.0";
		return autoCode;
	}

	public String[] testTurningLeft105() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "-105.0";
		return autoCode;
	}

	public String[] testTurningRight105() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "105.0";
		return autoCode;
	}

	public String[] testTurningLeft170() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "-170.0";
		return autoCode;
	}

	public String[] testTurningRight170() {
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "170.0";
		return autoCode;
	}


	// ARC TURNING 40kPa
	// public String[] hopperToBoilerBlue() {
	// String[] autoCode = new String[1];
	// autoCode[0] = arcTurn + ";" + "0.65" + ";" + "48.0" + ";" + "90.0" + ";"
	// + "false";
	// return autoCode;
	// }
	//
	// public String[] hopperToBoilerRed() {
	// String[] autoCode = new String[1];
	// autoCode[0] = arcTurn + ";" + "0.65" + ";" + "48.0" + ";" + "90.0" + ";"
	// + "true";
	// return autoCode;
	// }
}