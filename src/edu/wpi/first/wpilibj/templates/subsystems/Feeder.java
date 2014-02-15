package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
public class Feeder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Victor motor1, motor2;
    
    public Feeder() {
        motor1 = new Victor(RobotMap.feederMotorPorts[0]);
        motor2 = new Victor(RobotMap.feederMotorPorts[1]);
    }
    
    public void set(double s) {
        motor1.set(s);
        motor2.set(s);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}