package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;

public class Climber {
	CANTalon climberMaster, climberFollower;
	
	public Climber(){
		climberMaster = new CANTalon(Constants.CLIMBER_MASTER_PORT);
		climberFollower = new CANTalon(Constants.CLIMBER_MASTER_PORT);
		climberFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		climberFollower.set(climberMaster.getDeviceID());
	}
	
	public void climb(double speed){
		climberMaster.set(speed);
	}
	public void stopClimbing(){
		climberMaster.set(0);
	}
	public void checkVoltage(){
		if(climberMaster.getBusVoltage() > 9){	//TODO test how large volts should be
			stopClimbing();
		}
	}
}
