package org.usfirst.frc.team20.robot;

public class Constants {
	//CANTalons
	public static final int DRIVETRAIN_MASTER_RIGHT_MOTOR_PORT = 3;
	public static final int DRIVETRAIN_MASTER_LEFT_MOTOR_PORT = 1;
	public static final int DRIVETRAIN_FOLLOWER_RIGHT_MOTOR_PORT = 2;
	public static final int DRIVETRAIN_FOLLOWER_LEFT_MOTOR_PORT = 0;
	public static final int CLIMBER_MOTOR_PORT = 4;
	public static final int COLLECTOR_MOTOR_PORT = 3;	//L-board 2017
	public static final int FLYWHEEL_MOTOR_PORT = 6;	//L-board 2017
	
	//Encoders
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 0;
	public static final int DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 0;
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 0;
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 0;
	
	//JoySticks
	public static final int DRIVER_JOYSTICK_PORT = 0;	//actual port
	public static final int OPERATOR_JOYSTICK_PORT = 1;	//actual port
	public static final int JOYSTICK_RIGHT_TRIGGER = 3;	//actual port
	public static final int JOYSTICK_LEFT_TRIGGER = 2;	//actual port
	public static final int JOYSTICK_RIGHT_AXIS_UPDOWN = 5;	//actual port
	public static final int JOYSTICK_LEFT_AXIS_UPDOWN = 0; //TODO get port number
	
	//Solenoids
	public static final int DRIVETRAIN_EXTEND_PORT = 1;
	public static final int DRIVERTRAIN_RETRACT_PORT = 2;
	public static final int COLLECTOR_EXTEND_PORT = 3;
	public static final int COLLECTOR_RETRACT_PORT = 4;
	public static final int GEAR_EXTEND_PORT = 5;
	public static final int GEAR_RETRACT_PORT = 6;

	//Sockets
	public static final int VISION_SOCKET_PORT_NUMBER = 0;
	public static final int DIAGNOSTICS_SOCKET_PORT_NUMBER = 1;
	
	//FlyWheel PID
	public static final double FLYWHEEL_P = 0;
	public static final double FLYWHEEL_I = 0;
	public static final double FLYWHEEL_D = 0;
	public static final double FLYWHEEL_F = 0;
}
