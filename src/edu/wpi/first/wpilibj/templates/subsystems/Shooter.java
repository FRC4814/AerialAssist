/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.Drive;
import edu.wpi.first.wpilibj.templates.commands.DriveTest;
import edu.wpi.first.wpilibj.tools.CustMath;
import edu.wpi.first.wpilibj.tools.ScalableSubsystem;

/**
 *
 * @author Patrick
 */
public class Shooter extends ScalableSubsystem {

    double p = 1.0;
    double i = 0.0;
    double d = 0.0;

    public Shooter() {
        left = new SpeedControllerPID(RobotMap.shooterEncoderPortsL[0], RobotMap.shooterEncoderPortsL[1], RobotMap.shooterMotorPortsLeft, true, "LeftShooter");
        right = new SpeedControllerPID(RobotMap.shooterEncoderPortsR[0], RobotMap.shooterEncoderPortsR[1], RobotMap.shooterMotorPortsRight, false, "RightShooter");
        left.getPIDController().setPID(p, i, d);
        right.getPIDController().setPID(p, i, d);
        error = 0.0;
    }

    //command, get distance of 2 motors if one is higher lower the speed
    public void set(double s) {
        left.drive(s);
        right.drive(s);
    }

    protected void initDefaultCommand() {
    
    }
}
