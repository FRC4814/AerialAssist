package edu.wpi.first.wpilibj.tools;

import edu.wpi.first.wpilibj.Joystick;

public class Attack3Joystick extends Joystick {

    double deadband = 0.15;
    
    public Attack3Joystick(int port) {
        super(port);
    }
    
    public double getPower() {
        return (getZ()-1)/2;
    }
    
    public double getDeadY() {
        double y = getY();
        if(Math.abs(y) > deadband) {
            return y;
        }
        return 0;
    }
    
    public double getDeadX() {
        double x = getX();
        if(Math.abs(x) > deadband) {
            return x;
        }
        return 0;
    }
    
}
