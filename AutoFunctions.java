package org.usfirst.frc.team20.robot;

public class AutoFunctions {
	DriveTrain drive;
	FlyWheel flywheel;

	public AutoFunctions(DriveTrain d, FlyWheel f){
		drive = d;
		flywheel = f;
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
		flywheel.flyWheeltoSpeedEncoders(5000);
	}
}
