/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.tools;

/**
 *
 * @author Patrick
 */
public class CustMath {

    public static double goodifyInput(double d) {
        return 1.23607 * Math.sin(1.16497 * Math.sin(0.942478 * d));
    }

    public static double skim(double p) {
        double alpha = 0.6d;
        // gain determines how much to skim off the top
        if (p > 1.0) {
            return -((p - 1.0))*alpha;
        } else if (p < -1.0) {
            return -((p + 1.0))*alpha;
        }
        return 0;
    }
}
