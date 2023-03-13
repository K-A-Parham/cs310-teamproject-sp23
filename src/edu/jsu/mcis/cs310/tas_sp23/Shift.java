package edu.jsu.mcis.cs310.tas_sp23;


//import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;




public class Shift {
    
    private final String description;
    private final int id, roundinterval, graceperiod, dockpenalty, lunchthreshold;
    private final LocalTime shiftstart, shiftstop, lunchstart, lunchstop;
    
    private int lunchDuration; // minutes
    private int shiftDuration; // minutes
    
    public Shift(HashMap<String, String> shiftParams) {
        
        this.id = Integer.parseInt( shiftParams.get("id") );
        this.roundinterval = Integer.parseInt( shiftParams.get("roundinterval") );
        this.graceperiod = Integer.parseInt( shiftParams.get("graceperiod") );
        this.dockpenalty = Integer.parseInt( shiftParams.get("dockpenalty") );
        this.lunchthreshold = Integer.parseInt( shiftParams.get("lunchthreshold") );
        
        this.description = shiftParams.get("description");
        
        String shiftstartStr = shiftParams.get("shiftstart");
        LocalTime parsedTime = LocalTime.parse(shiftstartStr); //this.shiftstart  LocalTime parsedDate
        this.shiftstart = parsedTime;
                
        String shiftstopStr = shiftParams.get("shiftstop");
        LocalTime parsedTime_two = LocalTime.parse(shiftstopStr);
        this.shiftstop = parsedTime_two;
        
        String lunchstartStr = shiftParams.get("lunchstart");
        LocalTime parsedTime_three = LocalTime.parse(lunchstartStr);
        this.lunchstart = parsedTime_three;
        //this.lunchstart = lunchstartStr;
        
        String lunchstopStr = shiftParams.get("lunchstop");
        LocalTime parsedTime_four = LocalTime.parse(lunchstopStr);
        this.lunchstop = parsedTime_four;
        
        this.shiftDuration = (int)ChronoUnit.MINUTES.between(shiftstart, shiftstop);
        this.lunchDuration = (int)ChronoUnit.MINUTES.between(lunchstart, lunchstop);
        
    }
    
    public int getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalTime getShiftStart() {
        return shiftstart;
    }
    
    public LocalTime getShiftStop() {
        return shiftstop;
    }
    
    public int getRoundInterval() {
        return roundinterval;
    }
    
    public int getGracePeriod() {
        return graceperiod;
    }
    
    public int getDockPenalty() {
        return dockpenalty;
    }
    
    public LocalTime getLunchStart() {
        return lunchstart;
    }
    
    public LocalTime getLunchStop() {
        return lunchstop;
    }
    
    public int getLunchThreshold() {
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
        
        // "Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)"

        StringBuilder s = new StringBuilder();

        s.append(description).append(':');
        s.append(' ').append(shiftstart).append(' ').append('-');
        s.append(' ').append(shiftstop).append(' ');
        s.append('(').append(shiftDuration).append(" minutes)");
        s.append(';').append(' ').append("Lunch").append(':');
        s.append(' ').append(lunchstart).append(' ').append('-');
        s.append(' ').append(lunchstop).append(' ');
        s.append('(').append(lunchDuration).append(" minutes)");

        return s.toString();

    }

}
