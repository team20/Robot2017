package org.usfirst.frc.team20.robot;

public class AutoModes {
	AutoFunctions functions;
	public AutoModes(AutoFunctions f){
		functions = f;
	}
	
	public void doNothing(){	//robot does not move	
		
	}
	
	public void crossBaseline(){	//robot drives forward to cross the baseline
		functions.crossBaseline();
	}

	public void middlePeg(){
		functions.toMiddlePeg();
	}

	public void sidePeg(){
		functions.toSidePeg();
	}
	
//	public void rightPegRed(){
//		functions.toSidePeg();
////		functions.toRightPegRed();
//	}
//	
//	public void rightPegBlue(){
//		functions.toSidePeg();
////		functions.toRightPegBlue();
//	}
//	
//	public void leftPegRed(){
//		functions.toSidePeg();
////		functions.toLeftPegRed();
//	}
//	
//	public void leftPegBlue(){
//		functions.toSidePeg();
////		functions.toLeftPegBlue();
//	} 
	
	public void middleHopperRed(){	//robot puts a gear on the middle peg and then triggers a hopper
		functions.toMiddlePeg();
		functions.middlePegToHopperRed();
	}
	
	public void middleHopperBlue(){	//robot puts a gear on the middle peg and then triggers a hopper
		functions.toMiddlePeg();
		functions.middlePegToHopperBlue();
	}
	
	public void leftHopperRed(){	//robot puts a gear on the left peg and then triggers a hopper
		functions.toSidePeg();
//		functions.toLeftPegRed();
		functions.leftPegToHopperRed();
	}

	public void leftHopperBlue(){	//robot puts a gear on the left peg and then triggers a hopper
		functions.toSidePeg();
//		functions.toLeftPegBlue();
		functions.leftPegToHopperBlue();
	}

	public void rightHopperRed(){	//robot puts a gear on the right peg and then triggers a hopper
		functions.toSidePeg();
//	functions.toRightPegRed();
		functions.rightPegToHopperRed();
	}

	public void rightHopperBlue(){	//robot puts a gear on the right peg and then triggers a hopper
		functions.toSidePeg();
//	functions.toRightPegBlue();
		functions.rightPegToHopperBlue();
	}
	
	public void middleBoilerRed(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerRed();
		functions.shoot();
	}

	public void middleBoilerBlue(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerBlue();
		functions.shoot();
	}

	public void leftBoilerRed(){	//robot puts a gear on the left peg and then shoots at the boiler
		functions.toSidePeg();
//	functions.toLeftPegRed();
		functions.leftPegToBoilerRed();
		functions.shoot();
	}

	public void leftBoilerBlue(){	//robot puts a gear on the left peg and then shoots at the boiler
		functions.toSidePeg();
//	functions.toLeftPegBlue();
		functions.leftPegToBoilerBlue();
		functions.shoot();
	}

	public void rightBoilerRed(){	//robot puts a gear on the right peg and then shoots at the boiler
		functions.toSidePeg();
//	functions.toRightPegRed();
		functions.rightPegToBoilerRed();
		functions.shoot();
	}

	public void rightBoilerBlue(){	//robot puts a gear on the right peg and then shoots at the boiler
		functions.toSidePeg();
//	functions.toRightPegBlue();
		functions.rightPegToBoilerBlue();
		functions.shoot();
	}
	
	public void hopperBoilerRed(){	//robot dumps hopper and then shoots at boiler
		functions.toHopperRed();
		functions.hopperToBoilerRed();
		functions.shoot();
	}
	
	public void hopperBoilerBlue(){	//robot dumps hopper and then shoots at boiler
		functions.toHopperBlue();
		functions.hopperToBoilerBlue();
		functions.shoot();
	}

	public void startBoilerRed(){	//robot shoots at boiler
		functions.wallToBoilerRed();
		functions.shoot();
	}
	
	public void startBoilerBlue(){	//robot shoots at boiler
		functions.wallToBoilerBlue();
		functions.shoot();
	}
}
