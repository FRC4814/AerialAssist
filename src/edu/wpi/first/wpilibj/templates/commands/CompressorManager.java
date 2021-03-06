package edu.wpi.first.wpilibj.templates.commands;


public class CompressorManager extends CommandBase {

    public CompressorManager() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis
        requires(compressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(compressor.isFull() || !compressor.enabled) {
            compressor.off();
        } else {
            compressor.on();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}