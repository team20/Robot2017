package org.usfirst.frc.team20.robot;

public class Constants {
	//CANTalons
	public static final int DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT = 4;	//Right Top
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE = 2; //Right Front
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO = 3; //Right Back
	public static final int DRIVETRAIN_MASTER_LEFT_MOTOR_PORT = 10; //Left Top
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE = 9; //Left Front
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO = 8; //Left Back
	public static final int CLIMBER_MASTER_PORT = 1; //Climb Right
	public static final int CLIMBER_FOLLOWER_PORT = 11; //Climb Left
	public static final int GROUND_COLLECTOR_MOTOR_PORT = 5; //Collector
	public static final int FLYWHEEL_MASTER_PORT = 6; //Fly Wheel
	public static final int FUEL_TANK_COLLECTOR_MOTOR_PORT = 7; //Fuel Tank

	//Solenoids
	public static final int DRIVETRAIN_EXTEND_PORT = 0;
	public static final int DRIVERTRAIN_RETRACT_PORT = 1;
	public static final int TANK_EXTEND_PORT = 2;
	public static final int TANK_RETRACT_PORT = 3;
	public static final int GEAR_EXTEND_PORT = 4;
	public static final int GEAR_RETRACT_PORT = 5;

	//Sockets
	public static final int PI_SOCKET_PORT_NUMBER = 0;
	
	//FlyWheel
	public static final double FLYWHEEL_P = 0.15; //0.003
	public static final double FLYWHEEL_I = 0.0; //0.0
	public static final double FLYWHEEL_D = 0.0; //0.0
	public static final double FLYWHEEL_F = 0.034;	//0.032
	public static final double FLYWHEEL_SPEED = 3800.0;
	public static final double FLYWHEEL_DEADBAND = 60.0;

	//Bump Switch
	public static final int GEAR_BUMP_SWITCH_PORT_ONE = 0;
	
	//Misc.
	public static final double DRIVING_P = 0.030;	//TODO 0.020 practice, 0.010 comp
	public static final double DRIVING_P_LEFT = 0.018;	// 0.018 90, 
	public static final double DRIVING_P_RIGHT = 0.013;	//TODO 0.012 90, 0.0135 110
	public static final double TURNING_DEADBAND = 2.0;
	public static final double CLIMBER_MAX_VOLTAGE = 70.0; // Changed from 50 to 70 on 3/12. Still stalls occasionally.
	public static final double WAIT_GEAR_TIME = 0.25;	//seconds
}