
public class GroundCollector {
	CANTalon elevatorMotorFront;
	CANTalon elevatorMotorBack;

	public GroundCollector(){
		elevatorMotorFront = new CANTalon(0);
		elevatorMotorBack = new CANTalon(0);
	}

	public void intake(double speed) {
		elevatorMotorFront.set(speed);
		elevatorMotorBack.set(-speed);
	}

	public void outtake(double speed) {
		elevatorMotorFront.set(-speed);
		elevatorMotorBack.set(speed);
	}
	private DoubleSolenoid collectorPistons = new DoubleSolenoid(constants.COLLECTER_FIRST_SOLENOID_PORT,
			constants.COLLECTER_SECOND_SOLENOID_PORT);

	public void Collecters() {

	public void retractCollectors() {
		collectorPistons.set(DoubleSolenoid.Value.kReverse);
	}

	public void actuateCollectors() {
		collectorPistons.set(DoubleSolenoid.Value.kForward);
	}

	public void neturalCollectors() {
		collectorPistons.set(DoubleSolenoid.Value.kOff);
	}
}
}
