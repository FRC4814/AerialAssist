/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.tools.PurpleInput;
import edu.wpi.first.wpilibj.tools.ScalableSubsystem;

/**
 *
 * @author Patrick
 */
public class Shooter extends ScalableSubsystem {

    public PurpleInput bottomSwitch;
    public PurpleInput topSwitch;
    
    double p = 1.0;
    double i = 0.0;
    double d = 0.0;

    final double GOAL_V = 1.0;
    final double INC = 0.01;

    public boolean forward = true;

    public int height;
    int vel;

    double leftV = 0.0;
    double rightV = 0.0;

    public Shooter() {
        left = new SpeedControllerPID(RobotMap.shooterEncoderPortsL[0], RobotMap.shooterEncoderPortsL[1], RobotMap.shooterMotorPortsLeft, true, "LeftShooter");
        right = new SpeedControllerPID(RobotMap.shooterEncoderPortsR[0], RobotMap.shooterEncoderPortsR[1], RobotMap.shooterMotorPortsRight, false, "RightShooter");
        //left.getPIDController().setPID(p, i, d);
        //right.getPIDController().setPID(p, i, d);
        error = 0.0;
        bottomSwitch = new PurpleInput(RobotMap.shooterLowSwitch, true);
        topSwitch = new PurpleInput(RobotMap.shooterHighSwitch, true);
    }

    //command, get distance of 2 motors if one is higher lower the speed
    public void set(double s) {
        left.drive(s);
        right.drive(s);
    }

    public void resetV() {
        leftV = 0.0;
        rightV = 0.0;
    }

    public void manageDown() {
        left.drive(-0.5);
        right.drive(-0.5);
    }
    
    public void creepDown() {
        left.drive(-0.2);
        right.drive(-0.2);
    }

    public void manageUp() {
        left.drive(leftV);
        right.drive(rightV);
    }

    public double diffPos() {
        if (forward) {
            return left.getD() - right.getD(); //+ve when left is farther
        } else {
            return right.getD() - left.getD(); //+ve when left is farther
        }
    }

    public void equalizeVelocity() {
        double d = diffPos();
        if (d < 0.0) {
            leftV += INC;
        } else if (d > 0.0) {
            rightV += INC;
        }

        if (leftV > GOAL_V) {
            rightV -= (leftV - GOAL_V);
            leftV = GOAL_V;
        }
        if (rightV > GOAL_V) {
            leftV -= (rightV - GOAL_V);
            rightV = GOAL_V;
        }
    }

    public void limitVelocity() {
        double leftR = left.enc.getRate();
        double rightR = right.enc.getRate();
        if (leftR > vel) {
            leftV -= INC;
        }
        if (rightR > vel) {
            rightV -= INC;
        } else if (leftR < vel && rightR < vel && leftV < GOAL_V && rightV < GOAL_V) {
            leftV += INC * 10;
            rightV += INC * 10;
        }
    }

    protected void initDefaultCommand() {
    }

    public void update(int tarHeight, int tarVel) {
        height = tarHeight;
        vel = tarVel;
    }
}