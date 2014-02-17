package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.StartFeeder;
import edu.wpi.first.wpilibj.templates.commands.StopFeeder;
public class Feeder extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Victor motor;
    private boolean isSpinning = false;
    
    public Feeder() {
        motor = new Victor(RobotMap.feederMotorPort);
    }
    
    private void set(double s) {
        motor.set(s);
    }
    
    public void feedIn() {
        set(-1.0);
    }
    
    public void eject() {
        set(1.0);
    }
    
    public void stop() {
        set(0.0);
    }
    
    public void toggle() {
        if(isSpinning) {
            new StopFeeder().start();
        } else {
            new StartFeeder().start();
        }
        isSpinning = !isSpinning;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}