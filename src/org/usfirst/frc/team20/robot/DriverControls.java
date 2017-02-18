package org.usfirst.frc.team20.robot;

public class DriverControls {
	Controller driverJoy;
	DriveTrain drive;
	double speedStraight, speedLeft, speedRight;
	public DriverControls(DriveTrain d){
		driverJoy = new Controller(0);
		drive = d;
		speedStraight = 0;
		speedLeft = 0;
		speedRight = 0;
	}
	
	public void driverControls(){
		//Driver Code with the NavX Not Working
		if(drive.testNavx() < -180 || drive.testNavx() > 180){
			System.out.println("NavX Not Working");
			speedStraight = driverJoy.getLeftYAxis();
			speedLeft = driverJoy.getLeftTriggerAxis();
			speedRight = driverJoy.getRightTriggerAxis();
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
		speedStraight = driverJoy.getLeftYAxis();
		if(driverJoy.getLeftXAxis() < 0){
			speedLeft = driverJoy.getLeftXAxis();			
		}
		if(driverJoy.getLeftXAxis() > 0){
			speedRight = driverJoy.getLeftXAxis();			
		}
		if (speedStraight != 0 || speedLeft < 0 || speedRight > 0) {
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
