package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.PassOut;
import edu.wpi.first.wpilibj.templates.commands.SetDriveHighGear;
import edu.wpi.first.wpilibj.templates.commands.SetDriveLowGear;
import edu.wpi.first.wpilibj.templates.commands.ToggleFeeder;
import edu.wpi.first.wpilibj.templates.commands.ToggleFeederSolenoid;
import edu.wpi.first.wpilibj.templates.commands.Turn;
import edu.wpi.first.wpilibj.tools.Attack3Joystick;
import edu.wpi.first.wpilibj.templates.commands.RaiseShooter;
import edu.wpi.first.wpilibj.templates.commands.ResetShooter;
import edu.wpi.first.wpilibj.templates.commands.ShooterAimForReset;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public Attack3Joystick leftJoystick = new Attack3Joystick(RobotMap.leftJoystickPort);
    public Attack3Joystick rightJoystick = new Attack3Joystick(RobotMap.rightJoystickPort);
    public Attack3Joystick commandJoystick = new Attack3Joystick(RobotMap.commandJoystickPort);
    Button driveHighButton = new JoystickButton(rightJoystick, 1);
    Button driveLowButton = new JoystickButton(leftJoystick, 1);
    Button feederSolenoidButton = new JoystickButton(commandJoystick, 3);
    Button feederButton = new JoystickButton(commandJoystick, 1);
    Button shooterButton = new JoystickButton(commandJoystick, 4);
    Button shooterLowerButton = new JoystickButton(commandJoystick, 5);
    Button passButton = new JoystickButton(commandJoystick, 2);
    Button turnButton = new JoystickButton(rightJoystick, 2);
    Button shooterReset = new JoystickButton(commandJoystick, 8);

    public OI() {
        driveHighButton.whenReleased(new SetDriveHighGear());
        driveLowButton.whenReleased(new SetDriveLowGear());
        feederSolenoidButton.whenReleased(new ToggleFeederSolenoid());
        feederButton.whenReleased(new ToggleFeeder());
        passButton.whileHeld(new PassOut());
        turnButton.whileHeld(new Turn());
        shooterButton.whileHeld(new RaiseShooter());
        shooterLowerButton.whileHeld(new ResetShooter());
        shooterReset.whileHeld(new ShooterAimForReset());
    }
}
