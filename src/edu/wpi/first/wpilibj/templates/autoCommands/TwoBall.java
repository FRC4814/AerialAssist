/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.autoCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.Shoot;
import edu.wpi.first.wpilibj.templates.commands.ToggleFeeder;
import edu.wpi.first.wpilibj.templates.commands.ToggleFeederSolenoid;

/**
 *
 * @author Alibero
 */
public class TwoBall extends CommandGroup {
    
    public TwoBall() {
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
        addParallel(new ToggleFeederSolenoid());
        addParallel(new DriveForward(13600, CommandBase.driveTrain.left));
        addSequential(new DriveForward(11600, CommandBase.driveTrain.right));
        addSequential(new Shoot());
        addParallel(new ToggleFeeder());
        addSequential(new Wait(0.3));
        addParallel(new ToggleFeeder());
        addSequential(new Shoot());
        addParallel(new DriveForward(-13600, CommandBase.driveTrain.left));
        addParallel(new DriveForward(-11600, CommandBase.driveTrain.right));
        addSequential(new ToggleFeederSolenoid());
    }
}
