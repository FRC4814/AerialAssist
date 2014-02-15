/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.tools;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Patrick
 */
public class Attack3Joystick extends Joystick {

    public Attack3Joystick(int port) {
        super(port);
    }
    
    public double getPower() {
        return (getZ()-1)/2;
    }
    
    public double getDeadY() {
        double y = getY();
        if(Math.abs(y) > 0.2) {
            return y;
        }
        return 0;
    }
    
}
