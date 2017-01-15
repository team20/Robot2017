package org.usfirst.frc.team20.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

public class Navx {
	AHRS gyro = new AHRS(SerialPort.Port.kMXP);
	double currentAngle;
	double turnAngle;
	DriveTrain drive;
	Constants constants;
	
	public Navx(DriveTrain d){
		drive = d;
	}
	
	public void turnToAngle(double angle){
		angle = turnAngle;
		currentAngle = gyro.getYaw();
		if(turnAngle > 0){
			//TODO turn right
		}
		if(turnAngle < 0){
			//TODO turn left
		}
		else{
			//do nothing
		}
	}
	
	public void reset(){
		gyro.reset();
	}
}

