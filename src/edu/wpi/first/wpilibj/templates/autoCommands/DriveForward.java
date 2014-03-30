package edu.wpi.first.wpilibj.templates.autoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.SpeedControllerPID;


public class DriveForward extends CommandBase {

    double goal;
    SpeedControllerPID a;
    
    public DriveForward(int dist, SpeedControllerPID a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.a = a;
        requires(a);
        goal = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        a.enc.start();
        a.enc.reset();
        a.setSetpoint(goal);
        a.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("LeftEncoder", driveTrain.left.getD());
        SmartDashboard.putNumber("RightEncoder", driveTrain.right.getD());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return a.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        a.disable();
        //a.enc.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}