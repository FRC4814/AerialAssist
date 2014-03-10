package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

public class DriveSolenoid extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    DoubleSolenoid solenoid;
    boolean highGear = true;

    public DriveSolenoid() {
        solenoid = new DoubleSolenoid(RobotMap.driveSolenoidPorts[0], RobotMap.driveSolenoidPorts[1]);
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setLow() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        highGear = false;
    }

    public void setHigh() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        highGear = true;
    }

    public void toggle() {
        if (solenoid.get() == DoubleSolenoid.Value.kForward) {
            setLow();
        } else if (solenoid.get() == DoubleSolenoid.Value.kReverse) {
            setHigh();
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
