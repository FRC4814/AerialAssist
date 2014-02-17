package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

public class FeederSolenoid extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    DoubleSolenoid solenoid;

    public FeederSolenoid() {
        solenoid = new DoubleSolenoid(RobotMap.feederSolenoidPorts[0], RobotMap.feederSolenoidPorts[1]);
    }

    private void setFwd() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    private void setBkwd() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void toggle() {
        if (solenoid.get() == DoubleSolenoid.Value.kForward) {
            setBkwd();
        } else if (solenoid.get() == DoubleSolenoid.Value.kReverse) {
            setFwd();
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
