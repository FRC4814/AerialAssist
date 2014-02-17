 package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Compressor;
import edu.wpi.first.wpilibj.templates.subsystems.DriveSolenoid;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.subsystems.Feeder;
import edu.wpi.first.wpilibj.templates.subsystems.FeederSolenoid;
import edu.wpi.first.wpilibj.templates.subsystems.RaspberryPi;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
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
    public static Compressor compressor;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        driveTrain = new DriveTrain();
        raspberryPi = new RaspberryPi();
        feeder = new Feeder();
        shooter = new Shooter();
        //driveSolenoid = new DriveSolenoid();
        //compressor = new Compressor();
        //feederSolenoid = new FeederSolenoid();
        
        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(driveTrain);
        SmartDashboard.putData("Start Feeder", new StartFeeder());
        SmartDashboard.putData("Stop Feeder", new StopFeeder());
        SmartDashboard.putData("Auto", new Auto());
        SmartDashboard.putData("Encoders On", new DriveEncodersOn());
        SmartDashboard.putData("Reset Encoders", new DriveEncodersReset());
        SmartDashboard.putNumber("LeftEncoder", driveTrain.left.getD());
        SmartDashboard.putNumber("RightEncoder", driveTrain.right.getD());
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}