package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.NoShooterMovement;
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Victor motors[] = new Victor[4];
    
    public Shooter() {
        for(int a = 0; a < 4; a++) {
            motors[a] = new Victor(RobotMap.shooterPorts[a]);
        }
    }
    
    public void set(double s) {
        motors[0].set(s);
        motors[1].set(s);
        motors[2].set(s);
        motors[3].set(s);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new NoShooterMovement());
    }
}