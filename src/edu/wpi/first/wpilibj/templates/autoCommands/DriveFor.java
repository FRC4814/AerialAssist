package edu.wpi.first.wpilibj.templates.autoCommands;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;


public class DriveFor extends CommandBase {

    double direction;
    
    public DriveFor(double direction, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
        this.direction = direction;
        setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        driveTrain.drive(direction, direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}