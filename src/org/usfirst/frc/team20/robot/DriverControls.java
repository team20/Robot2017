package org.usfirst.frc.team20.robot;

public class DriverControls {
	Controller driverJoy;
	DriveTrain drive;
	Climber climb;
	double speedStraight, speedLeft, speedRight;
	AutoFunctions functions;
	boolean driveShift;
	public DriverControls(DriveTrain d, Climber c, AutoFunctions f){
		driverJoy = new Controller(0);
		drive = d;
		climb = c;
		functions = f;
		speedStraight = 0;
		speedLeft = 0;
		speedRight = 0;
		driveShift = false;
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
		if(driverJoy.getRightTriggerAxis() > 0.1){
			speedStraight = driverJoy.getRightTriggerAxis();
		}else if(driverJoy.getLeftTriggerAxis() > 0.1){
			speedStraight = -driverJoy.getLeftTriggerAxis();
 		}else{
 			speedStraight = 0.0;
 		}
		if(driverJoy.getLeftXAxis() < -0.1){
			speedLeft = -driverJoy.getLeftXAxis();			
		}else{
			speedLeft = 0.0;
		}
		if(driverJoy.getLeftXAxis() > 0.1){
			speedRight = driverJoy.getLeftXAxis();
		}else{
			speedRight = 0.0;
		}
		if (speedStraight > 0 || speedStraight < 0 || speedLeft > 0 || speedRight > 0) {
			drive.drive(speedStraight, speedRight, speedLeft);
		}else{
			drive.stopDrive();
		}
		if (driverJoy.getButtonY()) {
			drive.shiftHigh();
		}
		if(driverJoy.getButtonB()){
			drive.shiftLow();
		}
		if(driverJoy.getButtonA()){
			climb.climb(1);
		}
		System.out.println(climb.climberMaster.getOutputCurrent() + "\t" + climb.climberFollower.getOutputCurrent());
		if(climb.climberMaster.getOutputCurrent()>50 || climb.climberFollower.getOutputCurrent()>50){
			climb.stopClimbing();
		}
		if(driverJoy.getButtonX()){
			climb.stopClimbing();
		}
//		if(driverJoy.getButtonA()){
//			functions.visionTarget();
//		}
	}
}