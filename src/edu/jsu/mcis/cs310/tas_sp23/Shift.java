package edu.jsu.mcis.cs310.tas_sp23;


import java.util.HashMap;

/**
 *
 * @author Brandon Pugh
 */


public class Shift {
    
    private final String id, description, shiftstart, shiftstop, roundinterval;
    private final String graceperiod, dockpenalty, lunchstart, lunchstop, lunchthreshold;
    private int lunchDuration; // minutes
    private int shiftDuration; // minutes
    
    
    public Shift(HashMap<String, String> shiftParams) {
        String idStr = shiftParams.get("id");
        this.id = idStr;
        
        String descriptionStr = shiftParams.get("description");
        this.description = descriptionStr;
        
        String shiftstartStr = shiftParams.get("shiftstart");
        this.shiftstart = shiftstartStr;
        
        String shiftstopStr = shiftParams.get("shiftstop");
        this.shiftstop = shiftstopStr;
        
        String roundintervalStr = shiftParams.get("roundinterval");
        this.roundinterval = roundintervalStr;
        
        String graceperiodStr = shiftParams.get("graceperiod");
        this.graceperiod = graceperiodStr;
        
        String dockpenaltyStr = shiftParams.get("dockpenalty");
        this.dockpenalty = dockpenaltyStr;
        
        String lunchstartStr = shiftParams.get("lunchstart");
        this.lunchstart = lunchstartStr;
        
        String lunchstopStr = shiftParams.get("lunchstop");
        this.lunchstop = lunchstopStr;
        
        String lunchthresholdStr = shiftParams.get("lunchthreshold");
        this.lunchthreshold = lunchthresholdStr;
        
        
        String lunchStr = shiftParams.get("lunchduration");
        this.lunchDuration = Integer.parseInt(lunchStr);
        
        String shiftStr = shiftParams.get("shiftduration");
        this.shiftDuration = Integer.parseInt(shiftStr);
    }
    
    public String getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getShiftStart() {
        return shiftstart;
    }
    
    public String getShiftStop() {
        return shiftstop;
    }
    
    public String getRoundInterval() {
        return roundinterval;
    }
    
    public String getGracePeriod() {
        return graceperiod;
    }
    
    public String getDockPenalty() {
        return dockpenalty;
    }
    
    public String getLunchStart() {
        return lunchstart;
    }
    
    public String getLunchStop() {
        return lunchstop;
    }
    
    public String getLunchThreshold() {
        return lunchthreshold;
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
    
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(lunchDuration).append(' ');
        s.append('(').append(shiftDuration).append(')');

        return s.toString();

    }

}
