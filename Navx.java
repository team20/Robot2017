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
	
	public double getYaw(){
		double yaw = gyro.getYaw();
		return yaw;
	}
	public double getPitch(){
		return gyro.getPitch();
	}
	public double getRoll(){
		return gyro.getRoll();
	}
	public void reset(){
		gyro.reset();
	}
}

