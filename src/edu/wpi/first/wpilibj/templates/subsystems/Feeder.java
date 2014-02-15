package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
public class Feeder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Victor motor;
    
    public Feeder() {
        motor = new Victor(RobotMap.feederMotorPort);
    }
    
    public void set(double s) {
        motor.set(s);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}