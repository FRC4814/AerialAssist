package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StraightifyDrive extends CommandBase {

    public StraightifyDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double l = -oi.leftJoystick.getDeadY();
        double r = -oi.rightJoystick.getDeadY();
        if (l != 0.0) {
            l /= l;
        }
        if (r != 0.0) {
            r /= r;
        }
        driveTrain.drive(l, r);
        //driveTrain.drive(-oi.leftJoystick.getY(), -oi.rightJoystick.getY());
        SmartDashboard.putNumber("LeftEncoder", driveTrain.left.getD());
        SmartDashboard.putNumber("RightEncoder", driveTrain.right.getD());
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
