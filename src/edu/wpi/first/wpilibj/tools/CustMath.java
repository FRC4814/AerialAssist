package edu.wpi.first.wpilibj.tools;

public class CustMath {

    public static double goodifyInput(double d) {
        return 1.23607 * Math.sin(1.16497 * Math.sin(0.942478 * d));
    }

    public static double skim(double p, double a) {
        // gain determines how much to skim off the top
        if (p > 1.0) {
            return -((p - 1.0))*a;
        } else if (p < -1.0) {
            return -((p + 1.0))*a;
        }
        return 0;
    }
    
    public static double cap(double a) {
        if(a > 1.0) {
            return 1.0;
        } else if (a < -1.0) {
            return -1.0;
        }
        return a;
    }
}
