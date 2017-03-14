//Author: Victor Ghercoias
package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;

public class Climber {
	CANTalon climberMaster, climberFollower;
	
	public Climber(){
		climberMaster = new CANTalon(Constants.CLIMBER_MASTER_PORT);
		climberMaster.reverseOutput(false);
		climberFollower = new CANTalon(Constants.CLIMBER_FOLLOWER_PORT);
		climberFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		climberFollower.set(climberMaster.getDeviceID());
		climberFollower.reverseOutput(true);
	}
	
	public void climb(double speed){
		if(climberMaster.getOutputCurrent() > 70){ // Changed from 50 to 70 on 3/12. Still stalls occasionally.
			stopClimbing();
		}else{
			climberMaster.set(speed);			
		}
	}
	public void stopClimbing(){
		climberMaster.set(0);
	}
}
