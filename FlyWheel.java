package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class FlyWheel {
	CANTalon flywheel = new CANTalon(Constants.FLYWHEEL_MOTOR_PORT);
	Constants constants;
	public FlyWheel(Constants c){
		constants = c;
	}	
	public void shoot(double speed){
		flywheel.changeControlMode(TalonControlMode.Voltage);
		flywheel.set(-speed);
	}
	public void setF(double f){
		flywheel.setF(f);
	}
	public void shootWithEncoders(double RPMS, double p, double i, double d){
		flywheel.changeControlMode(TalonControlMode.Speed);
		flywheel.setP(p);
		flywheel.setI(i);
		flywheel.setD(d);
		//flywheel.setP(5.0);
		//flywheel.setI(.00001);
		//flywheel.setD(.00001);
		System.out.println(RPMS + "RPMS");
		double cps = RPMS/60*1024;	//cycles per second
		System.out.println(cps + "CPS");
		flywheel.set(cps);
	}
	public void stopFlywheel(){
		flywheel.set(0);
	}
}
