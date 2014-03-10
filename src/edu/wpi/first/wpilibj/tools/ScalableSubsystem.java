package edu.wpi.first.wpilibj.tools;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.SpeedControllerPID;
public abstract class ScalableSubsystem extends Subsystem {

    public double leftScale = 1d;
    public double rightScale = 1d;
    public double error;
    
    public SpeedControllerPID left;
    public SpeedControllerPID right;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void slowLeft() {
        leftScale -= 0.01;
        SmartDashboard.putNumber(getName() + "Scale", leftScale);
    }
    public void slowRight() {
        rightScale -= 0.01;
        SmartDashboard.putNumber(getName() + "Scale", rightScale);
    }
    public void reset() {
        left.enc.reset();
        right.enc.reset();
    }
    public double getDiscrepancy() {
        return right.getD() - left.getD();
    }
}