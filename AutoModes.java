package org.usfirst.frc.team20.robot;

public class AutoModes {
	DriveTrain drive; 
	FlyWheel flywheel;
	public AutoModes(DriveTrain d, FlyWheel f){
		drive = d;
		flywheel = f;
	}
	
	public void doNothing(){	//robot does not move	

	}	
	public void crossBaseline(){	//robot drives forward to cross the baseline
		drive.driveTimeStraight(1, 5); //TODO tune time spent driving
	}
	public void middlePeg(){
		
	}
	public void leftPegRed(){
		
	}
	public void leftPegBlue(){
		
	}
	public void rightPegRed(){
		
	}
	public void rightPegBlue(){
		
	}
	public void middleToHopperRed(){
		
	}
	public void middleToHopperBlue(){
		
	}
	public void leftToHopperRed(){
		
	}
	public void leftToHopperBlue(){
		
	}
	public void rightToHopperRed(){
		
	}
	public void rightToHopperBlue
	(){
	
	}
	public void middleToBoiler(){
		
	}
	public void leftToBoiler(){
		
	}
	public void rightToBoiler(){
		
	}
	public void shoot(){
		
	}

//******************************************************************************************	
	public void middleHopperRed(){	//robot puts a gear on the middle peg and then triggers a hopper
		//TODO	
	}
	
	public void middleHopperBlue(){	//robot puts a gear on the middle peg and then triggers a hopper
		//TODO
	}
	
	public void leftHopperRed(){	//robot puts a gear on the left peg and then triggers a hopper
		//TODO
	}

	public void leftHopperBlue(){	//robot puts a gear on the left peg and then triggers a hopper
		//TODO
	}

	public void rightHopperRed(){	//robot puts a gear on the right peg and then triggers a hopper
		//TODO
	}

	public void rightHopperBlue(){	//robot puts a gear on the right peg and then triggers a hopper
		//TODO
	}
	
	public void middleBoilerRed(){	//robot puts a gear on the middle peg and then shoots at the boiler
		//TODO
	}

	public void middleBoilerBlue(){	//robot puts a gear on the middle peg and then shoots at the boiler
		//TODO
	}

	public void leftBoilerRed(){	//robot puts a gear on the left peg and then shoots at the boiler
		//TODO
	}

	public void leftBoilerBlue(){	//robot puts a gear on the left peg and then shoots at the boiler
		//TODO
	}

	public void rightBoilerRed(){	//robot puts a gear on the right peg and then shoots at the boiler
		//TODO
	}

	public void rightBoilerBlue(){	//robot puts a gear on the right peg and then shoots at the boiler
		//TODO
	}

}
