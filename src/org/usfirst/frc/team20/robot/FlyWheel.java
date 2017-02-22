	package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

public class FlyWheel {
	CANTalon flywheelMaster;
	CANTalon flywheelFollower;
	boolean flywheelEncoder;
	public FlyWheel(){
		flywheelMaster = new CANTalon(Constants.FLYWHEEL_MASTER_PORT);
		flywheelFollower = new CANTalon(Constants.FLYWHEEL_FOLLOWER_PORT);
		flywheelFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		flywheelFollower.set(flywheelMaster.getDeviceID());
		flywheelFollower.reverseOutput(true);
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
//		if (!flywheelEncoder) {
//			System.out.println("Flywheel Encoder Not Working");
//			flywheelMaster.changeControlMode(TalonControlMode.PercentVbus);
//			flywheelMaster.set(0.75);
//		}
		flywheelMaster.changeControlMode(TalonControlMode.Speed);
		System.out.println(RPMS + "RPMS");
		double cps = RPMS/600*4096;	//cycles per second
		System.out.println(cps + "CPS");
		flywheelMaster.set(cps);
	}
	public boolean flywheelReady(double RPMS){
		if(RPMS - 100 < flywheelSpeed() && flywheelSpeed() < RPMS + 100){
			return true;
		}else{
			return false;
		}
	}
	public double flywheelSpeed(){
		//System.out.println("V Bus Flywheel " + flywheelMaster.getOutputVoltage());
		//System.out.print(flywheelMaster.getOutputCurrent());
		//System.out.print(",");
		return flywheelMaster.getSpeed()/4096*10*60;
		
	}
	public void stopFlywheel(){
		flywheelMaster.set(0);
	}
	public boolean flyCurrent(){
//		if (flywheelMaster.getOutputCurrent() >= 100 || flywheelFollower.getOutputCurrent() >= 100){
			return false;
//		}
//		return true;
	}
}
 
