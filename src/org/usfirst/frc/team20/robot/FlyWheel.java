package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

public class FlyWheel {
	CANTalon flywheelMaster, flywheelFollower;
	
	public FlyWheel(){
		flywheelMaster = new CANTalon(Constants.FLYWHEEL_MASTER_PORT);
		flywheelFollower = new CANTalon(Constants.FLYWHEEL_FOLLOWER_PORT);
		flywheelFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		flywheelFollower.set(flywheelMaster.getDeviceID());
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
	public void shootWithEncoders(double RPMS){
		boolean flywheelEncoder = false;
		try{
			FeedbackDeviceStatus sensorStatusFlywheel = flywheelMaster.isSensorPresent(FeedbackDevice.QuadEncoder);
			flywheelEncoder = (FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatusFlywheel);
		}catch(Exception e){
			System.out.println("Flywheel Encoders Error: " + e.toString() + "              " + flywheelEncoder);
			flywheelMaster.changeControlMode(TalonControlMode.PercentVbus);
			flywheelMaster.set(0.75);
		}
		flywheelMaster.changeControlMode(TalonControlMode.Speed);
		System.out.println(RPMS + "RPMS");
		double cps = RPMS/60*1024;	//cycles per second
		System.out.println(cps + "CPS");
		flywheelMaster.set(cps);
	}
	public boolean flywheelReady(){
		if(2900 < flywheelSpeed() && flywheelSpeed() < 3000){
			return true;
		}else{
			return false;
		}
	}
	public double flywheelSpeed(){
		return flywheelMaster.getSpeed()/4096*10*60;
	}
	public void stopFlywheel(){
		flywheelMaster.set(0);
	}
}
 