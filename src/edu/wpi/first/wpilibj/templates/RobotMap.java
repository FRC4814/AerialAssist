package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int leftJoystickPort = 1;
    public static final int rightJoystickPort = 2;
    public static final int commandJoystickPort = 3;
    
    public static final int driveMotorPortsLeft[] = {5, 6};
    public static final int driveMotorPortsRight[] = {1, 2};
    public static final int shooterMotorPortsLeft[] = {3, 4};
    public static final int shooterMotorPortsRight[] = {7, 8};
    public static final int feederMotorPort = 9;
    
    public static final int compressorPorts[] = {5, 1};
    public static final int driveSolenoidPorts[] = {1, 2};
    public static final int feederSolenoidPorts[] = {3, 4};

    public static final int encoderPortsL[] = {3, 4};
    public static final int encoderPortsR[] = {1, 2};
    public static final int shooterEncoderPortsL[] = {6, 9};
    public static final int shooterEncoderPortsR[] = {7, 8};
    
    public static final int shooterLowSwitch = 10;
    public static final int shooterHighSwitch = 11;
}