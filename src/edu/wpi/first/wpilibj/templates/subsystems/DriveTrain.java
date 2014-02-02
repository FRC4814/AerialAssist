/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.Drive;

/**
 *
 * @author Patrick
 */
public class DriveTrain extends Subsystem{

    SpeedControllerPID left;
    SpeedControllerPID right;

    public DriveTrain() {
        left = new SpeedControllerPID(RobotMap.encoderPortL1, RobotMap.encoderPortL2, RobotMap.driveMotorPortsLeft);
        right = new SpeedControllerPID(RobotMap.encoderPortR1, RobotMap.encoderPortR2, RobotMap.driveMotorPortsRight);
    }
    
    public void drive(double l, double r) {
        left.drive(l);
        right.drive(r);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
}