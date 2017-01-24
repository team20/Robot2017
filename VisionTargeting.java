package org.usfirst.frc.team20.robot;

import java.io.IOException;

public class VisionTargeting {
	private String ipAddress;
	private int port;
	private Util pi;
	private double[] data;
	
	public VisionTargeting(String ipAddress){
		this.ipAddress = ipAddress;
		pi = new Util();
	}
	
	public void updateImage(){
		try {
			data = Parse.parseString(pi.readSocket(ipAddress, port, "Test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getFirstAngle(){
		return data[0];
	}
	
	public double getFirstDistance(){
		return data[1];
	}
	
	public double getSecondAngle(){
		return data[2];
	}
	
	public double getSecondDistance(){
		return data[3];
	}
}
