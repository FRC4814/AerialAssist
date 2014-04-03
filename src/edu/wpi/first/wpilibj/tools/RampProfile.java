package edu.wpi.first.wpilibj.tools;

public class RampProfile {

    int width;
    int rampTime;
    int rampDown;
    int direction = 1;

    public RampProfile(int width, int rampTime) {
        this.width = width;
        this.rampTime = rampTime;
        rampDown = width - rampTime;
        if (width < 0) {
            direction = -1;
        }
    }

    public double getV(int current) {
        if (current >= width) {
            return 0.0;
        } else if (current >= rampDown) {
            return Math.max(((double) (width - current)) / rampTime, 0.15) * direction;
        }
        return (double) direction;
    }
}
