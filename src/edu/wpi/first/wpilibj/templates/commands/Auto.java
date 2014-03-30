package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto extends CommandGroup {

    public Auto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        //addParallel(new StartFeeder());
        addParallel(new DriveForward(100, CommandBase.driveTrain.left));
        addSequential(new DriveForward(100, CommandBase.driveTrain.right));
        /*addSequential(new Wait(1.0));
        addParallel(new StopFeeder());
        addParallel(new DriveForward(-1.0, CommandBase.driveTrain.left));
        addSequential(new DriveForward(-1.0, CommandBase.driveTrain.right));*/
    }
}