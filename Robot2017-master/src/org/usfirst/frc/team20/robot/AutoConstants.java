package org.usfirst.frc.team20.robot;

public class AutoConstants {	//TODO tune all distances and angles
	public static final double TICKS_PER_INCH = 667;

	//Flywheel Stuff
	public static final double FLYWHEEL_SPEED = 3000.0;
	public static final String FLYWHEEL_DELAY_START_BOILER = "2";
	
	//Hard Distances
	public static final String CROSS_BASELINE_DISTANCE = "50";
	public static final String MIDDLE_PEG_DISTANCE = "94";	
	public static final String SIDE_PEG_HARD_DISTANCE = "90";
	public static final String BACK_UP_FROM_PEG_DISTANCE = "-5";
	public static final String BACK_UP_FROM_BOILER_DISTANCE = "-30";
	public static final String BOILER_TO_MIDDLE_PEG_DISTANCE = "50";
	public static final String MIDDLE_PEG_TO_BOILER_DISTANCE_ONE = "50";
	public static final String MIDDLE_PEG_TO_BOILER_DISTANCE_TWO = "5";
	public static final String SIDE_PEG_TO_BOILER_DISTANCE = "20";

	//Hard Angles
	public static final String LEFT_PEG_HARD_ANGLE = "-20";
	public static final String RIGHT_PEG_HARD_ANGLE = "20";
	public static final String BOILER_TO_SIDE_PEG_HARD_ANGLE = "180";
	public static final String BOILER_TO_MIDDLE_PEG_HARD_ANGLE_RED = "-90";
	public static final String BOILER_TO_MIDDLE_PEG_HARD_ANGLE_BLUE = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_ONE = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_ONE = "-90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_RED_TWO = "90";
	public static final String MIDDLE_PEG_TO_BOILER_HARD_ANGLE_BLUE_TWO = "-90";
	public static final String SIDE_PEG_TO_BOILER_ANGLE = "180";

	//40kPa Stuff
	public static final double ARC_SPEED = 0.85;
	//Red
	public static final String ARC_TURN_ONE_LONG_RED = "93";
	public static final String ARC_TURN_ONE_SHORT_RED = "56";
	public static final String ARC_TURN_TWO_LONG_RED = "-77";
	public static final String ARC_TURN_TWO_SHORT_RED = "-56";
	public static final String KPA_HARD_ANGLE_RED = "135";
	public static final String KPA_HARD_DISTANCE_RED = "10";
	//Blue
	public static final String ARC_TURN_ONE_LONG_BLUE = "93";
	public static final String ARC_TURN_ONE_SHORT_BLUE = "56";
	public static final String ARC_TURN_TWO_LONG_BLUE = "-77";
	public static final String ARC_TURN_TWO_SHORT_BLUE = "-56";
	public static final String KPA_HARD_ANGLE_BLUE = "-135";
	public static final String KPA_HARD_DISTANCE_BLUE = "10";
}
