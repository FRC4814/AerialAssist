package edu.wpi.first.wpilibj.tools;

import edu.wpi.first.wpilibj.DigitalInput;

public class PurpleInput extends DigitalInput {

    boolean reverse = false;
    
    public PurpleInput(int channel, boolean reverse) {
        super(channel);
        this.reverse = reverse;
    }
    
    public PurpleInput(int channel) {
        super(channel);
    }
    
    public boolean get() {
        return !(super.get() && reverse);  
    }
    
}
