package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class SpeedControllerPID extends PIDSubsystem {

    private static final double Kp = 1.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    Victor driveMotors[] = new Victor[3];

    public Encoder enc;
    
    int tolerance = 5;

    // Initialize your subsystem here
    public SpeedControllerPID(int a, int b, int[] arr) {
        super("SpeedControllerPID", Kp, Ki, Kd);
        enc = new Encoder(a, b);
        for (int i = 0; i < 3; i++) {
            driveMotors[i] = new Victor(arr[i]);
        }
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        setAbsoluteTolerance(tolerance);
    }

    public void initDefaultCommand() {
    }

    public void drive(double speed) {
        driveMotors[0].set(speed);
        driveMotors[1].set(speed);
        driveMotors[2].set(speed);
    }

    public double getD() {
        return enc.getDistance();
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