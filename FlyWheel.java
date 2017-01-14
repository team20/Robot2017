package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

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
