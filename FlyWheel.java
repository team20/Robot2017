package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class FlyWheel {
	CANTalon flywheelTalon = new CANTalon(Constants.FLYWHEEL_MOTOR_PORT);
	Constants constants;
	public FlyWheel(Constants c){
		constants = c;
	}
	
	public void flyWheeltoSpeedEncoders(double rpms){
		flywheelTalon.changeControlMode(TalonControlMode.Speed);
		flywheelTalon.configEncoderCodesPerRev(25);
		double ticks = rpms/60*25; //ticks per second
		
		flywheelTalon.set(ticks);
	}
	
	public void flywheelToSpeedWithVoltage(double voltage){
		flywheelTalon.changeControlMode(TalonControlMode.Voltage);
		flywheelTalon.set(voltage*20/100);
	}
	public void stopFlywheel(){
		flywheelTalon.set(0);
	}
}
