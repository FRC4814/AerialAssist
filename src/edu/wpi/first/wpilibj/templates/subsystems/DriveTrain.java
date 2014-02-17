/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.Drive;
import edu.wpi.first.wpilibj.templates.commands.DriveTest;
import edu.wpi.first.wpilibj.tools.ScalableSubsystem;

/**
 *
 * @author Patrick
 */
public class DriveTrain extends ScalableSubsystem {

    double p = 1.0;
    double i = 0.0;
    double d = 0.0;

    public DriveTrain() {
        left = new SpeedControllerPID(RobotMap.encoderPortsL[0], RobotMap.encoderPortsL[1], RobotMap.driveMotorPortsLeft, true, "LeftDrive");
        right = new SpeedControllerPID(RobotMap.encoderPortsR[0], RobotMap.encoderPortsR[1], RobotMap.driveMotorPortsRight, false, "RightDrive");
        left.getPIDController().setPID(p, i, d);
        right.getPIDController().setPID(p, i, d);
        error = 0.0;
        SmartDashboard.putNumber("DriveTrainLeftScale", leftScale);
        SmartDashboard.putNumber("DriveTrainRightScale", rightScale);
    }

    //command, get distance of 2 motors if one is higher lower the speed
    public void drive(double l, double r) {
        left.drive(l * leftScale);
        right.drive(r * rightScale);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    public void test() {
        double leftTemp = leftScale;
        double rightTemp = rightScale;
        CommandGroup test = new DriveTest(1.0);
        test.start();
        while (test.isRunning()) {
            Timer.delay(0.01);
        }
        if (leftScale != leftTemp || rightScale != rightTemp) {
            test();
        }
    }
}
