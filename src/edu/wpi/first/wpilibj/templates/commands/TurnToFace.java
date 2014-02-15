package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.RaspberryPi;

public class TurnToFace extends CommandBase {

    public TurnToFace() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
        requires(raspberryPi);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setInterruptible(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (raspberryPi.getX() + RaspberryPi.xOff < 300) {
            driveTrain.drive(-1, 1);
            RaspberryPi.xOff++;
        } else if (raspberryPi.getX() + RaspberryPi.xOff > 340) {
            driveTrain.drive(1, -1);
            RaspberryPi.xOff--;
        } else {
            driveTrain.drive(0, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        driveTrain.drive(0, 0);
        raspberryPi.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
