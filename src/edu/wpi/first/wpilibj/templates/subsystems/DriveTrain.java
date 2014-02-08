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
import edu.wpi.first.wpilibj.tools.ScalableSubsystem;

/**
 *
 * @author Patrick
 */
public class DriveTrain extends ScalableSubsystem{
    
    public DriveTrain() {
        left = new SpeedControllerPID(RobotMap.encoderPortL1, RobotMap.encoderPortL2, RobotMap.driveMotorPortsLeft);
        right = new SpeedControllerPID(RobotMap.encoderPortR1, RobotMap.encoderPortR2, RobotMap.driveMotorPortsRight);
        error = 0d;
    }
    //command, get distance of 2 motors if one is higher lower the speed
    public void drive(double l, double r) {
        left.drive(l*leftScale);
        right.drive(r*rightScale);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    
    public void test() {
        double leftTemp = leftScale;
        double rightTemp = rightScale;
        CommandGroup test = new DriveTest(1d);
        test.start();
        while(test.isRunning()) {
            Timer.delay(0.01);
        }
        if (leftScale != leftTemp || rightScale != rightTemp) {
            test();
        }
    }
}