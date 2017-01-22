package org.usfirst.frc.team20.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GroundCollector {
	CANTalon elevatorMotor;
	CANTalon elevatorMotorBack;
	Constants constants;
	public GroundCollector(Constants c){
		constants = c;
		elevatorMotor = new CANTalon(0);
	}
	public void intake(double speed) {
		elevatorMotor.set(speed);
	}
	public void outtake(double speed) {
		elevatorMotor.set(-speed);
	}
	private DoubleSolenoid collectorPistons = new DoubleSolenoid(constants.COLLECTOR_EXTEND_PORT,
			constants.COLLECTOR_RETRACT_PORT);

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
