package org.usfirst.frc.team20.robot;

public class AutoModesRightEncoder {
	AutoFunctionsRightEncoder functions;
	
	public AutoModesRightEncoder(AutoFunctionsRightEncoder f){
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

	public void rightPeg(){
		functions.toRightPeg();
	}
	
	public void leftPeg(){
		functions.toLeftPeg();
	}

	public void boilerToSidePeg(){
		functions.shoot(3000.0);
		functions.backUpFromBoilerTurnAndTarget();
	}

	public void middletankRed(){	//robot puts a gear on the middle peg and then triggers a tank
		functions.toMiddlePeg();
		functions.middlePegTotankRed();
	}
	
	public void middletankBlue(){	//robot puts a gear on the middle peg and then triggers a tank
		functions.toMiddlePeg();
		functions.middlePegTotankBlue();
	}
	
	public void lefttankRed(){	//robot puts a gear on the left peg and then triggers a tank
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegTotankRed();
	}

	public void lefttankBlue(){	//robot puts a gear on the left peg and then triggers a tank
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegTotankBlue();
	}

	public void righttankRed(){	//robot puts a gear on the right peg and then triggers a tank
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegTotankRed();
	}

	public void righttankBlue(){	//robot puts a gear on the right peg and then triggers a tank
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegTotankBlue();
	}
	
	public void middleBoilerRed(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerRed();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void middleBoilerBlue(){	//robot puts a gear on the middle peg and then shoots at the boiler
		functions.toMiddlePeg();
		functions.middlePegToBoilerBlue();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void leftBoilerRed(){	//robot puts a gear on the left peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToBoilerRed();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void leftBoilerBlue(){	//robot puts a gear on the left peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toLeftPeg();
		functions.leftPegToBoilerBlue();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void rightBoilerRed(){	//robot puts a gear on the right peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToBoilerRed();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void rightBoilerBlue(){	//robot puts a gear on the right peg and then shoots at the boiler
//		functions.toSidePeg();
		functions.toRightPeg();
		functions.rightPegToBoilerBlue();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}
	
	public void tankBoilerRed(){	//robot dumps tank and then shoots at boiler
		functions.totankRed();
		functions.tankToBoilerRed();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}
	
	public void tankBoilerBlue(){	//robot dumps tank and then shoots at boiler
		functions.totankBlue();
		functions.tankToBoilerBlue();
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}

	public void startBoiler(){	//robot shoots at boiler
		functions.shoot(3000.0);
		functions.stopFlywheel();
	}
}
