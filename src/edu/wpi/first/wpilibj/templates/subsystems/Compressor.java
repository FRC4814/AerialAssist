package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
public class Compressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    edu.wpi.first.wpilibj.Compressor compressor;
    
    public void Compressor() {
        compressor = new edu.wpi.first.wpilibj.Compressor(RobotMap.compressorPorts[0], RobotMap.compressorPorts[1]);
        compressor.start();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}