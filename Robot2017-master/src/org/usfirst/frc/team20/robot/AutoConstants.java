package org.usfirst.frc.team20.robot;

public class AutoConstants {
	public static final double TICKS_PER_INCH = 650.0;
	public static final double STOPPING_INCHES = 5.0;
	
	//Speeds
	public static final double CAMERA_TURN_SPEED = 0.35;
	public static final double CAMERA_DRIVE_SPEED = 0.35;
	public static final String DRIVE_STRAIGHT_SPEED = "0.60";	//0.35
	public static final String DRIVE_BOILER_SPEED = "0.85";
	public static final String TURN_SPEED = "0.35";
	public static final String SLAM_SPEED = "1.0";
	
	//Flywheel Stuff
	public static final double FLYWHEEL_SPEED = 3000.0;
	public static final String FLYWHEEL_DELAY_START_BOILER = "2";

	//40kPa Stuff
	public static final String KPA_WAIT_HOPPER = "2.0";
	public static final String KPA_STOPPING_TIME = "0.2";
	public static final String KPA_TURNING_DISTANCE = "10.0";
	
	public static final String KPA_ANGLE_ONE_RED = "0.0";
	public static final String KPA_ANGLE_TWO_RED = "-45.0";	//-90.0
	public static final String KPA_ANGLE_THREE_RED = "0.0";
	public static final String KPA_ANGLE_FOUR_RED = "0.0";		 
	public static final String KPA_ANGLE_FIVE_RED = "-90.0";		 
	public static final String KPA_ANGLE_SIX_RED = "0.0";		 
	public static final String KPA_ANGLE_SEVEN_RED = "50.0";		 
	public static final String KPA_ANGLE_EIGHT_RED = "0.0";		 

	//Red	//TODO fully fix the blue-red mix up
	public static final String KPA_ANGLE_ONE_BLUE = "0.0";		 
	public static final String KPA_ANGLE_TWO_BLUE = "45.0";	//90.0 
	public static final String KPA_ANGLE_THREE_BLUE = "0.0";
	public static final String KPA_ANGLE_FOUR_BLUE = "0.0";		 
	public static final String KPA_ANGLE_FIVE_BLUE = "90.0";		 
	public static final String KPA_ANGLE_SIX_BLUE = "0.0";		 
	public static final String KPA_ANGLE_SEVEN_BLUE = "-50.0";
	public static final String KPA_ANGLE_EIGHT_BLUE = "0.0";

	//Peg Autos
	public static final String SIDE_PEG_DISTANCE_ONE = "80.0";
	public static final String RIGHT_PEG_ANGLE = "-57.0";
	public static final String LEFT_PEG_ANGLE = "57.0";
	public static final String SIDE_PEG_DISTANCE_TWO = "27.0";
		
	//Peg to Boiler Autos
	public static final String BACK_UP_FROM_PEG_DISTANCE = "-20.0";
	public static final String SIDE_PEG_TO_BOILER_ANGLE_RED = "170.0";
	public static final String SIDE_PEG_TO_BOILER_ANGLE_BLUE = "-170.0";
	public static final String SIDE_PEG_TO_BOILER_DISTANCE = "90.0";

//	//40kPa Arc Turns
//	public static final double ARC_SPEED = 0.85;	
//	//Red
//	//Blue
}