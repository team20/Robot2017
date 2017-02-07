package org.usfirst.frc.team20.robot;

public class DriverControls {
	Controller driverJoy;
	DriveTrain drive;
	
	public DriverControls(DriveTrain d){
		driverJoy = new Controller(0);
		drive = d;
	}
	
	public void driverControls(){
		//Driver Code with the NavX Not Working
		try{
			drive.initializeNavx();
		}catch(Exception e){
			System.out.println("NavX Not Working	" + e.toString());
			double speedStraight = driverJoy.getLeftYAxis();
			double speedLeft = driverJoy.getLeftTriggerAxis();
			double speedRight = driverJoy.getRightTriggerAxis();
			if (speedStraight != 0 || speedLeft > 0 || speedRight > 0) {
				drive.masterRight.set(speedStraight - speedRight + speedLeft);
				drive.masterLeft.set(-speedStraight + speedLeft - speedRight);
			}
			if (driverJoy.getButtonLeftBumper()) {
				drive.shiftHigh();
			}
			if (driverJoy.getButtonRightBumper()) {
				drive.shiftLow();
			}			
		}
		
		//Driver Code with the NavX Working
		double speedStraight = driverJoy.getLeftYAxis();
		double speedLeft = driverJoy.getLeftTriggerAxis();
		double speedRight = driverJoy.getRightTriggerAxis();
		if (speedStraight != 0 || speedLeft > 0 || speedRight > 0) {
			drive.drive(speedStraight, speedRight, speedLeft);
		}
		if (driverJoy.getButtonLeftBumper()) {
			drive.shiftHigh();
		}
		if (driverJoy.getButtonRightBumper()) {
			drive.shiftLow();
		}

	}
}
