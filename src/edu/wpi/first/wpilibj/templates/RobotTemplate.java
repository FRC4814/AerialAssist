/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.templates.autoCommands.DoNothing;
import edu.wpi.first.wpilibj.templates.autoCommands.OneBall;
import edu.wpi.first.wpilibj.templates.autoCommands.TwoBall;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.UpdateSDB;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    CommandGroup autonomousCommand;
    SendableChooser autoChooser;
    Command updateCommand = new UpdateSDB();
    boolean autoStarted = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // Initialize all subsystems

        CommandBase.init();

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Do Nothing", new DoNothing());
        autoChooser.addObject("One Ball", new OneBall());
        autoChooser.addObject("Two Ball", new TwoBall());
        /*try {
         // schedule the autonomous command (example)
         CommandBase.raspberryPi.connect();
         } catch (IOException ex) {
         ex.printStackTrace();
         }*/
    }

    public void autonomousInit() {
        //CommandBase.raspberryPi.start();
        autonomousCommand = (CommandGroup) autoChooser.getSelected();
        autonomousCommand.start();
        autoStarted = true;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autoStarted) {
            autonomousCommand.cancel();
        }
        updateCommand.start();
        //CommandBase.raspberryPi.stop();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        Scheduler.getInstance().run();
    }
}
