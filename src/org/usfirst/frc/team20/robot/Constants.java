package org.usfirst.frc.team20.robot;

public class Constants {
	//CANTalons
	public static final int DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT = 4;	//Right Top
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE = 2; //Right Front
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO = 3; //Right Back
	public static final int DRIVETRAIN_MASTER_LEFT_MOTOR_PORT = 10; //Left Top
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE = 12; //Left Front
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO = 8; //Left Back
	public static final int CLIMBER_MASTER_PORT = 1; //Climb Right
	public static final int CLIMBER_FOLLOWER_PORT = 11; //Climb Left
	public static final int GROUND_COLLECTOR_MOTOR_PORT = 5; //Collector
	public static final int FLYWHEEL_MOTOR_PORT = 6; //Fly Wheel
	public static final int FLYWHEEL_FOLLOWER_PORT = 9;
	public static final int FUEL_TANK_COLLECTOR_MOTOR_PORT = 7; //Fuel Tank

//	public static final int FLYWHEEL_FOLLOWER_PORT = 10;
		
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
	public static final double FLYWHEEL_P = .5; //0.003
	public static final double FLYWHEEL_I = 0.0; //0.0
	public static final double FLYWHEEL_D = 0.0; //0.0
	public static final double FLYWHEEL_F = 0.031;	//0.0165
	public static final double FLYWHEEL_SPEED = 2900.0;

	//NavX PID
	public static final double NavX_P = 0.0025;//0.035;
	public static final double NavX_I = 0.00030;//0.00090;
	public static final double NavX_D = 0.00;
	public static final double NavX_F = 0.00;
	public static final double NavX_Tolerance_Degrees = 1.0f;
	
	//Bump Switch
	public static final int GEAR_BUMP_SWITCH_PORT_ONE = 0;
//	public static final int GEAR_BUMP_SWITCH_PORT_TWO = 1;
}