//Author: Daniel Pickett
package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;

public class FuelTank {
	CANTalon tankMotor;
	private DoubleSolenoid agitatorPistons;
	DriverStation station;
	double initialMatchTime;
	boolean tankToFlywheel;
	int counter = 0;
	boolean out = false;
	
	public FuelTank(){
		tankMotor = new CANTalon(Constants.FUEL_TANK_COLLECTOR_MOTOR_PORT);
		tankMotor.reverseOutput(false);
		agitatorPistons = new DoubleSolenoid(Constants.TANK_EXTEND_PORT, Constants.TANK_RETRACT_PORT);
		station = DriverStation.getInstance();
		initialMatchTime = station.getMatchTime();
		tankToFlywheel = false;
		tankMotor.reverseOutput(false);
	}
	
	public void tankMotorIntoFlywheel(double speed){
		tankMotor.set(speed);
	}	
	public void tankMotorIntoTank(double speed){
		tankMotor.set(-speed);
	}
	public void stopTank(){
		tankMotor.set(0);
	}
	public void retractAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kReverse);
	}
	public void actuateAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kForward);
	}
	public void runAgitator(){
//		double currentMatchTime = station.getMatchTime();
//		if(initialMatchTime < 0){
//			initialMatchTime = currentMatchTime;
//		}
//		System.out.println(initialMatchTime + "              " + currentMatchTime);
//		if(initialMatchTime - 0.25 < currentMatchTime){
//			System.out.println("In the If");
//			retractAgitator();
//		}else{
//			if(initialMatchTime - 0.4 > currentMatchTime){
//				initialMatchTime = currentMatchTime;
//			}
//			actuateAgitator();
//		}
		counter++;
//		if(counter%20 == 0){
//			if(out){
//				retractAgitator();
//				out = !out;
//			}else{
//				actuateAgitator();
//				out = !out;
//				
//			}
//		}
		counter++;
//		if(counter%30 == 0 && counter % 10 == 0){
//			retractAgitator();
//		}else if(counter % 7 == 0){
//			actuateAgitator();
//		}
		
		if(counter==28){
			actuateAgitator();
		}else if(counter >= 84){
			retractAgitator();
			counter = 0;
		}
		
	}
}