package org.usfirst.frc.team20.robot;

public class AutoConstants {
	public static final double STOPPING_CONSTANT = 10.0;
	public static final double TICKS_PER_INCH = 657.0;   // Practice 650, Comp 657
	
	//Speeds
	public static final double CAMERA_TURN_SPEED = 0.35;
	public static final double CAMERA_DRIVE_SPEED = 0.35;
	public static final String DRIVE_STRAIGHT_SPEED = "0.60";	//0.35
	public static final String TURN_SPEED = "0.35";
	
	//Flywheel Stuff
	public static final double FLYWHEEL_SPEED = 3000.0;
	public static final String FLYWHEEL_DELAY_START_BOILER = "2";
	
	//Cross Baseline
	public static final String CROSS_BASELINE_DISTANCE = "120";
	
	//Peg Autos
	public static final String MIDDLE_PEG_DISTANCE = "87.0";
	public static final String RIGHT_PEG_HARD_DISTANCE = "70.0";
	public static final String LEFT_PEG_HARD_DISTANCE = "55.0";

	public static final String RIGHT_PEG_HARD_ANGLE = "-55.0";
	public static final String LEFT_PEG_HARD_ANGLE = "60.0";

	public static final String RIGHT_PEG_DISTANCE_TWO = "55.0";
	public static final String LEFT_PEG_DISTANCE_TWO = "60.0";
		
	//Back Up Distances
	public static final String BACK_UP_FROM_PEG_DISTANCE = "-5.0";
	public static final String BACK_UP_FROM_BOILER_DISTANCE = "-30.0";

	//Boiler to Peg Autos
	public static final String BOILER_TO_MIDDLE_PEG_DISTANCE = "50.0";
	public static final String MIDDLE_PEG_TO_BOILER_DISTANCE_ONE = "50.0";
	public static final String MIDDLE_PEG_TO_BOILER_DISTANCE_TWO = "5.0";
	public static final String SIDE_PEG_TO_BOILER_DISTANCE = "20.0";

	//Hard Angles
	public static final String BOILER_TO_SIDE_PEG_HARD_ANGLE = "180";
	public static final String BOILER_TO_MIDDLE_PEG_HARD_ANGLE_RED = "-90";
	public static final String BOILER_TO_MIDDLE_PEG_HARD_ANGLE_BLUE = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_ONE = "-90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_TWO = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_TWO = "-90";
	public static final String SIDE_PEG_TO_BOILER_ANGLE = "180";

	//40kPa Stuff
	public static final String KPA_WAIT_HOPPER = "2";		 
	//Red
	public static final String KPA_DISTANCE_ONE_RED = "90";	 
	public static final String KPA_ANGLE_ONE_RED = "0";		 
	public static final String KPA_DISTANCE_TWO_RED = "10";	 
	public static final String KPA_ANGLE_TWO_RED = "-90";	 
	public static final String KPA_DISTANCE_THREE_RED = "17";	 
	public static final String KPA_ANGLE_THREE_RED = "0";		 
	public static final String KPA_DISTANCE_FOUR_RED = "-20";	 
	public static final String KPA_ANGLE_FOUR_RED = "0";		 
	public static final String KPA_DISTANCE_FIVE_RED = "-25";	 
	public static final String KPA_ANGLE_FIVE_RED = "-90";		 
	public static final String KPA_DISTANCE_SIX_RED = "72";	 
	public static final String KPA_ANGLE_SIX_RED = "0";		 
	public static final String KPA_DISTANCE_SEVEN_RED = "20";	 
	public static final String KPA_ANGLE_SEVEN_RED = "50";		 
	public static final String KPA_DISTANCE_EIGHT_RED = "20";	 
	public static final String KPA_ANGLE_EIGHT_RED = "0";		 

	//Blue
	public static final String KPA_DISTANCE_ONE_BLUE = "90";	 
	public static final String KPA_ANGLE_ONE_BLUE = "0";		 
	public static final String KPA_DISTANCE_TWO_BLUE = "10";	 
	public static final String KPA_ANGLE_TWO_BLUE = "90";	 
	public static final String KPA_DISTANCE_THREE_BLUE = "17";	 
	public static final String KPA_ANGLE_THREE_BLUE = "0";
	public static final String KPA_DISTANCE_FOUR_BLUE = "-20";	 
	public static final String KPA_ANGLE_FOUR_BLUE = "0";		 
	public static final String KPA_DISTANCE_FIVE_BLUE = "-25";	 
	public static final String KPA_ANGLE_FIVE_BLUE = "90";		 
	public static final String KPA_DISTANCE_SIX_BLUE = "72";	 
	public static final String KPA_ANGLE_SIX_BLUE = "0";		 
	public static final String KPA_DISTANCE_SEVEN_BLUE = "20";	 
	public static final String KPA_ANGLE_SEVEN_BLUE = "-50";		 
	public static final String KPA_DISTANCE_EIGHT_BLUE = "20";	 
	public static final String KPA_ANGLE_EIGHT_BLUE = "0";

//	//40kPa Stuff
//	public static final double ARC_SPEED = 0.85;	
//	//Red
//	public static final String ARC_TURN_ONE_LONG_RED = "93";
//	public static final String ARC_TURN_ONE_SHORT_RED = "56";
//	public static final String ARC_TURN_TWO_LONG_RED = "-77";
//	public static final String ARC_TURN_TWO_SHORT_RED = "-56";
//	public static final String KPA_HARD_ANGLE_RED = "135";
//	public static final String KPA_HARD_DISTANCE_RED = "10";
//	//Blue
//	public static final String ARC_TURN_ONE_LONG_BLUE = "93";
//	public static final String ARC_TURN_ONE_SHORT_BLUE = "56";
//	public static final String ARC_TURN_TWO_LONG_BLUE = "-77";
//	public static final String ARC_TURN_TWO_SHORT_BLUE = "-56";
//	public static final String KPA_HARD_ANGLE_BLUE = "-135";
//	public static final String KPA_HARD_DISTANCE_BLUE = "10";
}