package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

public class FlyWheel {
	CANTalon flywheelMaster;
//	CANTalon flywheelFollower;
	boolean flywheelEncoder;
	public FlyWheel(){
		flywheelMaster = new CANTalon(Constants.FLYWHEEL_MASTER_PORT);
//		flywheelMaster.setStatusFrameRateMs(StatusFrameRate, 20);
//		flywheelFollower = new CANTalon(Constants.FLYWHEEL_FOLLOWER_PORT);
//		flywheelFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
//		flywheelFollower.set(flywheelMaster.getDeviceID());
//		flywheelFollower.reverseOutput(true);
		flywheelEncoder = false;
		flywheelMaster.configPeakOutputVoltage(12.0f, 0.0f);
	}	

	public void shoot(double speed){
		flywheelMaster.changeControlMode(TalonControlMode.Voltage);
		flywheelMaster.set(-speed);
	}
	public void setPID(double p, double i, double d, double f){
		flywheelMaster.setP(p);
		flywheelMaster.setI(i);
		flywheelMaster.setD(d);
		flywheelMaster.setF(f);
	}
	public boolean flywheelEncoder(){
		FeedbackDeviceStatus sensorStatusFlywheel = flywheelMaster.isSensorPresent(FeedbackDevice.QuadEncoder);
		flywheelEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusFlywheel);
		return flywheelEncoder;
	}
	public void shootWithEncoders(double RPMS){
		flywheelMaster.changeControlMode(TalonControlMode.Speed);
		double cps = RPMS/600*4096;
		flywheelMaster.set(cps);
	}
	public boolean flywheelReady(double RPMS){
		if(RPMS - 70 < flywheelSpeed() && flywheelSpeed() < RPMS + 70){
			return true;
		}else{
			return false;
		}
	}
	public double flywheelSpeed(){
		return flywheelMaster.getSpeed()/4096*600;
	}
	public void stopFlywheel(){
		flywheelMaster.set(0);
	}
}
 
