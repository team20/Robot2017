package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
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
	public void setF(double f){
		flywheelMaster.setF(f);
		flywheelFollower.setF(f);
	}
	public void shootWithEncoders(double RPMS, double p, double i, double d){
		flywheelMaster.changeControlMode(TalonControlMode.Speed);
		flywheelMaster.setP(p);
		flywheelMaster.setI(i);
		flywheelMaster.setD(d);
		//flywheelMaster.setP(5.0);
		//flywheelMaster.setI(.00001);
		//flywheelMaster.setD(.00001);
		System.out.println(RPMS + "RPMS");
		double cps = RPMS/60*1024;	//cycles per second
		System.out.println(cps + "CPS");
		flywheelMaster.set(cps);
	}
	public void stopFlywheel(){
		flywheelMaster.set(0);
	}
}
