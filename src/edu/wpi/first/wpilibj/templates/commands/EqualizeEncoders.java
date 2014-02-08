package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.tools.ScalableSubsystem;


public class EqualizeEncoders extends CommandBase {

    ScalableSubsystem sys;
    double time;
    int samples = 100;
    
    public EqualizeEncoders(ScalableSubsystem sys, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.time = time;
        setTimeout(time);
        this.sys = sys;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        sys.reset();
        Timer.delay(time/samples);
        if(sys.getDiscrepancy() > sys.error) {
            sys.slowRight();
        } else if (-sys.getDiscrepancy() > sys.error) {
            sys.slowLeft();
        }
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