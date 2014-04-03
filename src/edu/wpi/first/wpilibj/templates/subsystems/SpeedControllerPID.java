package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SpeedControllerPID extends Subsystem {

    Victor driveMotors[] = new Victor[2];

    public Encoder enc;
    int direction = 1;

    // Initialize your subsystem here
    public SpeedControllerPID(int a, int b, int[] arr, boolean reverse, String name) {
        super(name);
        enc = new Encoder(a, b, reverse, EncodingType.k4X);
        for (int i = 0; i < 2; i++) {
            driveMotors[i] = new Victor(arr[i]);
        }
        enc.start();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        if (reverse) {
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

    public int getD() {
        return -enc.get() * direction;
    }
}
