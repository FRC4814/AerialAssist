package edu.wpi.first.wpilibj.tools;

public class RampProfile {

    int width;
    int rampTime;
    int rampDown;
    
    public RampProfile (int width, int rampTime) {
        this.width = width;
        this.rampTime = rampTime;
        rampDown = width - rampTime;
    }
    
    public double getV(int current) {
        if(current <= rampTime) {
            return current/rampTime;
        } else if (current >= rampDown) {
            return (width - current)/rampTime;
        }
        return 1.0;
    }
}
