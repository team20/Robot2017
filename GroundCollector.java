package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GroundCollector {
	CANTalon elevatorMotor;
	CANTalon elevatorMotorBack;
	CANTalon flywheel;
	Constants constants;
	public GroundCollector(Constants c){
		constants = c;
		elevatorMotor = new CANTalon(Constants.COLLECTOR_MOTOR_PORT);
		flywheel = new CANTalon(Constants.FLYWHEEL_MOTOR_PORT);
	}
	public void intake(double speed) {
		elevatorMotor.set(speed);
	}

	public void outtake(double speed) {
		elevatorMotor.set(-speed);
	}
	public void stopCollector(){
		elevatorMotor.set(0);
	}
	
	private DoubleSolenoid collectorPistons = new DoubleSolenoid(Constants.COLLECTOR_EXTEND_PORT,
			Constants.COLLECTOR_RETRACT_PORT);

	public void retractCollector() {
		collectorPistons.set(DoubleSolenoid.Value.kReverse);
	}

	public void actuateCollectors() {
		collectorPistons.set(DoubleSolenoid.Value.kForward);
	}

	public void neturalCollectors() {
		collectorPistons.set(DoubleSolenoid.Value.kOff);
	}	
}
