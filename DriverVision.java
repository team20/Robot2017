package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.CameraServer;

public class DriverVision {

	public DriverVision(){
		
	}
	public void startUSBCamera(){
		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture("Normal Driver", 0);
	}
}
