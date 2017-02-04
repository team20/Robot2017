package org.usfirst.frc.team20.robot;

public class Constants {
	//CANTalons
	public static final int DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT = 0;
	public static final int DRIVETRAIN_MASTER_LEFT_MOTOR_PORT = 1;
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_ONE = 2;
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT_TWO = 11;
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_ONE = 5;
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT_TWO = 7;
	public static final int CLIMBER_MASTER_PORT = 8;
	public static final int CLIMBER_FOLLOWER_PORT = 9;
	public static final int GROUND_COLLECTOR_MOTOR_PORT = 3;	//L-board 2017
	public static final int FLYWHEEL_MASTER_PORT = 6;	//L-board 2017
	public static final int FLYWHEEL_FOLLOWER_PORT = 10;
	public static final int HOPPER_COLLECTOR_MOTOR_PORT = 4;
		
	//JoySticks
	public static final int DRIVER_JOYSTICK_PORT = 0;	//actual port
	public static final int OPERATOR_JOYSTICK_PORT = 1;	//actual port

	//Solenoids
	public static final int DRIVETRAIN_EXTEND_PORT = 7;
	public static final int DRIVERTRAIN_RETRACT_PORT = 2;
	public static final int HOPPER_EXTEND_PORT = 1;
	public static final int HOPPER_RETRACT_PORT = 0;
	public static final int GEAR_EXTEND_PORT = 5;
	public static final int GEAR_RETRACT_PORT = 6;

	//Sockets
	public static final int VISION_SOCKET_PORT_NUMBER = 0;
	public static final int DIAGNOSTICS_SOCKET_PORT_NUMBER = 1;
	
	//FlyWheel PID
	public static final double FLYWHEEL_P = 0.003;
	public static final double FLYWHEEL_I = 0.0;
	public static final double FLYWHEEL_D = 0.0;
	public static final double FLYWHEEL_F = 0.0165;
	
	//Bump Switch
	public static final int GEAR_BUMP_SWITCH_PORT_ONE = 0;
	public static final int GEAR_BUMP_SWITCH_PORT_TWO = 1;
}
