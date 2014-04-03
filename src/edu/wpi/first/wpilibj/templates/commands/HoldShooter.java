/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Alibero
 */
public class HoldShooter extends CommandBase {

    double holdVel = 0.4;
    int accuracy = 3;
    int pos;

    public HoldShooter(int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
        pos = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (shooter.left.getD() - accuracy > pos) {
            shooter.set(-holdVel);
        } else if (shooter.left.getD() + accuracy < pos) {
            shooter.set(holdVel);
        } else {
            shooter.set(0.0);
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
