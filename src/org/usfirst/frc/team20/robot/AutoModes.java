package org.usfirst.frc.team20.robot;

public class AutoModes {
	AutoFunctions functions;
	
	public AutoModes(AutoFunctions f){
		functions = f;
	}
	
	public void doNothing(){	//robot does not move	
		
	}
	
	public void auto1(){
		functions.autoMode1();
	}
	
	public void crossBaseline(){	//robot drives forward to cross the baseline
		functions.crossBaseline();
	}

	public void middlePeg(){
		functions.toMiddlePeg();
	}

	public void rightPeg(){
		functions.toRightPeg();
	}
	
	public void leftPeg(){
		functions.toLeftPeg();
	}
	
	public void boilerToSidePeg(){
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.backUpFromBoilerTurnAndTarget();
	}
		
	public void middleHopperRed(){	//robot puts a gear on the middle peg and then triggers a Hopper
		functions.toMiddlePeg();
		functions.middlePegToHopperRed();
	}
	
	public void middleHopperBlue(){	//robot puts a gear on the middle peg and then triggers a Hopper
		functions.toMiddlePeg();
		functions.middlePegToHopperBlue();
	}
	
	public void leftHopperRed(){	//robot puts a gear on the left peg and then triggers a Hopper
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToHopperRed();
	}

	public void leftHopperBlue(){	//robot puts a gear on the left peg and then triggers a Hopper
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToHopperBlue();
	}

	public void rightHopperRed(){	//robot puts a gear on the right peg and then triggers a Hopper
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToHopperRed();
	}

	public void rightHopperBlue(){	//robot puts a gear on the right peg and then triggers a Hopper
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToHopperBlue();
	}
	
	public void middleBoilerRed(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerRed();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void middleBoilerBlue(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerBlue();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void leftBoilerRed(){	//robot puts a gear on the left peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToBoilerRed();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void leftBoilerBlue(){	//robot puts a gear on the left peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToBoilerBlue();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void rightBoilerRed(){	//robot puts a gear on the right peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToBoilerRed();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void rightBoilerBlue(){	//robot puts a gear on the right peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToBoilerBlue();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}
	
	public void HopperBoilerRed(){	//robot dumps Hopper and then shoots at boiler
		functions.toHopperRed();
		functions.HopperToBoilerRed();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}
	
	public void HopperBoilerBlue(){	//robot dumps Hopper and then shoots at boiler
		functions.toHopperBlue();
		functions.HopperToBoilerBlue();
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}

	public void startBoiler(){	//robot shoots at boiler
		functions.shoot(AutoConstants.FLYWHEEL_SPEED );
		functions.stopFlywheel();
	}
}
