//Author: Sydney Walker
package org.usfirst.frc.team20.robot;

public class RocketScript {
	String getCameraAngle = "1"; // (angle, ip, port) //gets distance too
	String goCameraDistance = "2";
	String turn = "3";
	String arcTurn = "4";	//speed, radius of circle, turn angle, direction (right == true)
	String fastDriveStraight = "5";
	String timeDrive = "6";
	String shoot = "7";
	String stopShooting = "8";
	String wait = "9";
	String spinUp = "10";
	String lowGear = "11";

	public String[] testTurning(){
		String[] autoCode = new String[1];
		autoCode[0] = turn + ";" + "90.0";
		return autoCode;
	}

	public String[] middleGearTimed() {
		String[] autoCode = new String[7];
		autoCode[0] = timeDrive + ";" + "0.6" + ";" + "1.5"; //TODO MAKE SURE THIS IS FIVE SECONDS
		autoCode[1] = wait + ";" + "3.0";
		autoCode[2] = fastDriveStraight + ";" + "0.6" + ";" + "-30.0" + ";" + "0.0";
		autoCode[3] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "25.0" + ";" + "-90.0";
		autoCode[4] = fastDriveStraight + ";" + "0.6" + ";" + "130.0" + ";" + "0.0";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "25.0" + ";" + "90.0";
		autoCode[6] = fastDriveStraight + ";" + "0.6" + ";" + "200.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] stayAtBoilerAndShoot() {
		String[] autoCode = new String[3];
		autoCode[0] = shoot + ";" + "8.0";
		autoCode[1] = stopShooting + ";" + "NULL";
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-80.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] rightGearNC() {
		String[] autoCode = new String[7];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "18.0" + ";"
				+ AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + "3.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-30.0" + ";" + "0.0";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "15.0" + ";" + "60.0";
		autoCode[6] = fastDriveStraight + ";" + "1.0" + ";" + "200.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] leftGearNC() {
		String[] autoCode = new String[7];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "25.0" + ";"
				+ AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + "3.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-30.0" + ";" + "0.0";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "30.0" + ";" + "-60.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "200.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] rightGear() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5.0" + ";"
				+ AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = wait + ";" + "3.0";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-30.0" + ";" + "0.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "30.0" + ";" + "60.0";
		autoCode[7] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "200.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] leftGear() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5" + ";"
				+ AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = wait + ";" + "5.0";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "-30.0" + ";" + "0.0";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "30.0" + ";" + "-60.0";
		autoCode[7] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";" + "200.0" + ";" + "0.0";
		return autoCode;
	}

	public String[] rightGearToBoilerRedNC() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "20.0" + ";"
				+ AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + "3.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "40.0" + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_ANGLE_RED;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "20";
		return autoCode;
	}

	public String[] leftGearToBoilerBlueNC() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "30.0" + ";"
				+ AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_TWO + ";" + "0.00";
		autoCode[3] = wait + ";" + "3.0";
		autoCode[4] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "60.0" + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_ANGLE_RED;
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_BOILER_SPEED + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "20";
		return autoCode;
	}

	public String[] rightGearToBoilerRed() {	//TODO update gear placement code and check all (turn 180?)
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5" + ";"
				+ AutoConstants.RIGHT_PEG_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = wait + ";" + "2.5";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

	public String[] leftGearToBoilerBlue() {
		String[] autoCode = new String[8];
		autoCode[0] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_DISTANCE_ONE + ";" + "0.00";
		autoCode[1] = fastDriveStraight + ";" + AutoConstants.TURN_SPEED + ";" + "5" + ";"
				+ AutoConstants.LEFT_PEG_ANGLE;
		autoCode[2] = getCameraAngle + ";" + "NULL";
		autoCode[3] = goCameraDistance + ";" + "NULL";
		autoCode[4] = wait + ";" + "2.5";
		autoCode[5] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.BACK_UP_FROM_PEG_DISTANCE + ";" + "0.00";
		autoCode[6] = fastDriveStraight + ";" + AutoConstants.DRIVE_STRAIGHT_SPEED + ";"
				+ AutoConstants.SIDE_PEG_TO_BOILER_DISTANCE + ";" + "0.00";
		autoCode[7] = shoot + ";" + "0";
		return autoCode;
	}

 	public String[] hopperToBoilerRed() {
		String[] autoCode = new String[14];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "0.85"; //0.7
		autoCode[1] = timeDrive + ";" + "-0.5" + ";" + "0.2";
		autoCode[2] = turn + ";" + "40.0";
//		autoCode[2] = fastDriveStraight + ";" + "0.35" + ";" + "25.0" + ";" + "33.0";
		autoCode[3] = timeDrive + ";" + "0.5" + ";" + "0.7";
		autoCode[4] = wait + ";" + "1.5";
		autoCode[5] = timeDrive + ";" + "-1.0" + ";" + "0.15";
		autoCode[6] = timeDrive + ";" + "-0.5" + ";" + "0.2";
		autoCode[7] = turn + ";" + "90.0";
//		autoCode[7] = fastDriveStraight + ";" + "0.35" + ";" + "30.0" + ";" + "30.0";
		autoCode[8] = spinUp + ";" + "NULL";
		autoCode[9] = timeDrive + ";" + "1.0" + ";" + "0.5";
		autoCode[10] = timeDrive + ";" + "0.0" + ";" + "0.25";
		autoCode[11] = lowGear + ";" + "NULL";
		autoCode[12] = timeDrive + ";" + "1.0" + ";" + "1.0";
		autoCode[13] = shoot + ";" + "15";
		return autoCode;
	}

	public String[] hopperToBoilerBlue() {
		String[] autoCode = new String[14];
		autoCode[0] = timeDrive + ";" + "1.0" + ";" + "0.85"; // 0.7
		autoCode[1] = timeDrive + ";" + "-0.5" + ";" + "0.2";
		autoCode[2] = turn + ";" + AutoConstants.TURN_SPEED + ";" + "-40.0";
// 		autoCode[2] = fastDriveStraight + ";" + "0.35" + ";" + "25.0" + ";" + "-33.0";
		autoCode[3] = timeDrive + ";" + "0.5" + ";" + "0.7";
		autoCode[4] = wait + ";" + "1.5";
		autoCode[5] = timeDrive + ";" + "-1.0" + ";" + "0.15";
		autoCode[6] = timeDrive + ";" + "-0.5" + ";" + "0.2";
		autoCode[7] = turn + ";" + AutoConstants.TURN_SPEED + ";" + "-90.0";
//		autoCode[7] = fastDriveStraight + ";" + "0.35" + ";" + "30.0" + ";" + "-30.0";
		autoCode[8] = spinUp + ";" + "NULL";
		autoCode[9] = timeDrive + ";" + "1.0" + ";" + "0.5";
		autoCode[10] = timeDrive + ";" + "0.0" + ";" + "0.25";
		autoCode[11] = lowGear + ";" + "NULL";
		autoCode[12] = timeDrive + ";" + "1.0" + ";" + "1.0";
		autoCode[13] = shoot + ";" + "15";
		return autoCode;
	}

	//ARC TURNING 40kPa
//	public String[] hopperToBoilerBlue() {
//		String[] autoCode = new String[1];
//		autoCode[0] = arcTurn + ";" + "0.65" + ";" + "48.0" + ";" + "90.0" + ";" + "false";
//		return autoCode;
//	}
//
//	public String[] hopperToBoilerRed() {
//		String[] autoCode = new String[1];
//		autoCode[0] = arcTurn + ";" + "0.65" + ";" + "48.0" + ";" + "90.0" + ";" + "true";
//		return autoCode;
//	}
}