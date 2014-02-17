package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class SpeedControllerPID extends PIDSubsystem {

    private static final double Kp = 1.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    final static double meterToEncoder = 14600;

    Victor driveMotors[] = new Victor[2];

    public Encoder enc;
    int direction = 1;
    int tolerance = 5;

    // Initialize your subsystem here
    public SpeedControllerPID(int a, int b, int[] arr, boolean reverse, String name) {
        super(name, Kp, Ki, Kd);
        enc = new Encoder(a, b, reverse);
        for (int i = 0; i < 2; i++) {
            driveMotors[i] = new Victor(arr[i]);
        }
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        setAbsoluteTolerance(tolerance);
        if(reverse) {
            direction = -1;
        }
    }

    public void initDefaultCommand() {
    }

    public void drive(double speed) {
        speed *= direction;
        driveMotors[0].set(speed);
        driveMotors[1].set(speed);
    }

    public double getD() {
        return enc.getDistance()/meterToEncoder;
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return getD();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        drive(output);
    }
}