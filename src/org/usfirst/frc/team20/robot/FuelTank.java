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
	
	public FuelTank(){
		tankMotor = new CANTalon(Constants.FUEL_TANK_COLLECTOR_MOTOR_PORT);
		agitatorPistons = new DoubleSolenoid(1, 0);
		station = DriverStation.getInstance();
		initialMatchTime = station.getMatchTime();
		tankToFlywheel = false;
	}
	
	public void tankMotorIntoFlywheel(double speed){
		tankMotor.set(-speed);
	}	
	public void tankMotorIntoTank(double speed){
		tankMotor.set(speed);
	}
	public void stopTank(){
		tankMotor.set(0);
	}
	private void retractAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kReverse);
	}
	private void actuateAgitator() {
		agitatorPistons.set(DoubleSolenoid.Value.kForward);
	}
	public void runAgitator(){
		double currentMatchTime = station.getMatchTime();
		if(initialMatchTime < 0){
			initialMatchTime = currentMatchTime;
		}
		System.out.println(initialMatchTime + "              " + currentMatchTime);
		if(initialMatchTime - 0.25 < currentMatchTime){
			System.out.println("In the If");
			retractAgitator();
		}else{
			if(initialMatchTime - 0.4 > currentMatchTime){
				initialMatchTime = currentMatchTime;
			}
			actuateAgitator();
		}
	}
}