package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleFeederSolenoid extends CommandBase {

    public ToggleFeederSolenoid() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(feederSolenoid);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        feeder.isIn = true;
        feederSolenoid.toggle();
        setTimeout(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        if(!feederSolenoid.isSetIn()) {
            feeder.isIn = false;
        }
        SmartDashboard.putBoolean("ShooterIsIn", feeder.isIn);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}