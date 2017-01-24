package org.usfirst.frc.team20.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoFunctions {
	DriveTrain drive;
	FlyWheel flywheel;
	GroundCollector collector;
	public AutoFunctions(DriveTrain d, FlyWheel f, GroundCollector c){
		drive = d;
		flywheel = f;
		collector = c;
	}
	
	public void crossBaseline(){
		drive.driveTimeStraight(1, 4);
	}
	public void toMiddlePeg(){
		drive.driveTimeStraight(1, 2);
	}
	public void toLeftPegRed(){
		
	}
	public void toLeftPegBlue(){
		
	}
	public void toRightPegRed(){
		
	}
	public void toRightPegBlue(){
		
	}
	public void middlePegToHopperRed(){
		
	}
	public void middlePegToHopperBlue(){
		
	}
	public void leftPegToHopperRed(){
		
	}
	public void leftPegToHopperBlue(){
		
	}
	public void rightPegToHopperRed(){
		
	}
	public void rightPegToHopperBlue(){
	
	}
	public void middlePegToBoilerRed(){
		
	}
	public void middlePegToBoilerBlue(){
		
	}
	public void leftPegToBoilerRed(){
		
	}
	public void leftPegToBoilerBlue(){
		
	}
	public void rightPegToBoilerRed(){
		
	}
	public void rightPegToBoilerBlue(){
		
	}
	public void toHopperRed(){
		
	}
	public void toHopperBlue(){
		
	}
	public void hopperToBoilerRed(){
		
	}
	public void hopperToBoilerBlue(){
		
	}
	public void wallToBoilerRed(){
		
	}
	public void wallToBoilerBlue(){
		
	}
	public void shoot(){
		String rpm = SmartDashboard.getString("DB/String 0", "");
    	String p = SmartDashboard.getString("DB/String 1", "");
    	String i = SmartDashboard.getString("DB/String 2", "");
    	String d = SmartDashboard.getString("DB/String 3", "");
    	String f = SmartDashboard.getString("DB/String 4", "");    	
    	double RPM = Integer.parseInt(rpm);
       	flywheel.shootWithEncoder(RPM,p,i,d,f);
       	collector.intake(1);
       	System.out.println(flywheel.flywheel.getSpeed());
	}
}
