package edu.jsu.mcis.cs310.tas_sp23;


import java.util.HashMap;




public class Shift {
    private int lunchDuration; // minutes
    private int shiftDuration; // minutes
    private String name;
    private int id;
    
    
    
    public Shift(HashMap<String, String> shiftParams) {
        String lunchStr = shiftParams.get("lunchduration");
        this.lunchDuration = Integer.parseInt(lunchStr);
        
        String shiftStr = shiftParams.get("shiftduration");
        this.shiftDuration = Integer.parseInt(shiftStr);
    }
    
    public int getLunchDuration() {
        return this.lunchDuration;
    }
    
    public int getShiftDuration() {
        return this.shiftDuration;
    }
    
    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }
    
    public void setShiftDuration(int shiftDuration) {
        this.shiftDuration = shiftDuration;
    }
    // Basics of the shift class so far
    // Also wanted to make sure I could push it to Git
    
}