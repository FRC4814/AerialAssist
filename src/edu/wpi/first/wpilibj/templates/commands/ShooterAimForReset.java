package edu.wpi.first.wpilibj.templates.commands;

public class ShooterAimForReset extends CommandBase {

    public ShooterAimForReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.creepDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooter.bottomSwitch.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.set(0);
        shooter.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
