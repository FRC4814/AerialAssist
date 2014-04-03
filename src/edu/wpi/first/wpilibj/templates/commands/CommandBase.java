package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.DriveSolenoid;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.subsystems.Feeder;
import edu.wpi.first.wpilibj.templates.subsystems.FeederSolenoid;
import edu.wpi.first.wpilibj.templates.subsystems.PurpleCompressor;
import edu.wpi.first.wpilibj.templates.subsystems.RaspberryPi;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.tools.DebugValue;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveTrain driveTrain;
    public static RaspberryPi raspberryPi;
    public static Feeder feeder;
    public static Shooter shooter;
    public static DriveSolenoid driveSolenoid;
    public static FeederSolenoid feederSolenoid;
    public static PurpleCompressor compressor;
    public static DebugValue height = new DebugValue("ShooterHeight", 345);
    public static DebugValue rate = new DebugValue("ShooterRate", 100);
    

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        driveTrain = new DriveTrain();
        //raspberryPi = new RaspberryPi();
        feeder = new Feeder();
        shooter = new Shooter();
        compressor = new PurpleCompressor();
        feederSolenoid = new FeederSolenoid();
        driveSolenoid = new DriveSolenoid();
        oi = new OI();
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData("Toggle Feeder", new ToggleFeeder());
        SmartDashboard.putData("Manage Feeder", new ManageFeeder());
        SmartDashboard.putData("Reverse Feeder", new ReverseFeeder());
        SmartDashboard.putBoolean("ShooterIsIn", true);
        SmartDashboard.putData("EncodersReset", new EncodersReset());
        SmartDashboard.putNumber("LeftEncoder", driveTrain.left.getD());
        SmartDashboard.putNumber("RightEncoder", driveTrain.right.getD());
        SmartDashboard.putNumber("LeftDriveScale", driveTrain.leftScale);
        SmartDashboard.putNumber("RightDriveScale", driveTrain.rightScale);
        
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}