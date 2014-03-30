package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.CompressorManager;
public class PurpleCompressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean enabled = true;

    Relay compressor;
    DigitalInput pressureSwitch;
    
    public PurpleCompressor() {
        pressureSwitch = new DigitalInput(5);
        compressor = new Relay(1);
        compressor.setDirection(Relay.Direction.kForward);
    }
    
    public void on() {
        compressor.set(Relay.Value.kForward);
    }
    
    public void off() {
        compressor.set(Relay.Value.kOff);
    }
    
    public boolean isFull() {
        return pressureSwitch.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new CompressorManager());
    }
}