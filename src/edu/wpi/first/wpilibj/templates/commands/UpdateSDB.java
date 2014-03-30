package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateSDB extends CommandBase {

    public UpdateSDB() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("LeftEncoder", driveTrain.left.getD());
        SmartDashboard.putNumber("RightEncoder", driveTrain.right.getD());
        SmartDashboard.putNumber("LeftShooterEncoder", shooter.left.getD());
        SmartDashboard.putNumber("RightShooterEncoder", shooter.right.getD());
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
