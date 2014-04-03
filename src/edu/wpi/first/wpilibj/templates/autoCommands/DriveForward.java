package edu.wpi.first.wpilibj.templates.autoCommands;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.SpeedControllerPID;
import edu.wpi.first.wpilibj.tools.RampProfile;


public class DriveForward extends CommandBase {

    int goal;
    SpeedControllerPID a;
    RampProfile profile;
    
    public DriveForward(int dist, SpeedControllerPID a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.a = a;
        requires(a);
        goal = dist;
        profile = new RampProfile(goal, goal/10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        a.enc.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        a.drive(profile.getV(a.getD()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (a.getD() >= goal);
    }

    // Called once after isFinished returns true
    protected void end() {
        a.drive(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        a.drive(0.0);
    }
}