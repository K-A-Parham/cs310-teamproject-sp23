package edu.jsu.mcis.cs310.tas_sp23;


//import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
* <p>A Shift class that represents a shift rule set and contains the information for it.</p>
* @author Makiya Kirby and Brandon Pugh
*/
public class Shift {
    
    /**
    *
    * Instance fields used to hold information about a single shift rule set.
    */
    private final String description;
    private final int id, roundinterval, graceperiod, dockpenalty, lunchthreshold;
    private final LocalTime shiftstart, shiftstop, lunchstart, lunchstop;  
    private int lunchDuration;
    private int shiftDuration; 
    
    /**
    * <p>This is a constructor that retrieves variables from a hash map and converts some to their native type. </p>
    * @param shiftParams the hash map that holds shift rule set information/variables.
    */
    public Shift(HashMap<String, String> shiftParams) {
        
        this.id = Integer.parseInt( shiftParams.get("id") );
        this.roundinterval = Integer.parseInt( shiftParams.get("roundinterval") );
        this.graceperiod = Integer.parseInt( shiftParams.get("graceperiod") );
        this.dockpenalty = Integer.parseInt( shiftParams.get("dockpenalty") );
        this.lunchthreshold = Integer.parseInt( shiftParams.get("lunchthreshold") );
        
        this.description = shiftParams.get("description");
        
        String shiftstartStr = shiftParams.get("shiftstart");
        LocalTime parsedTime = LocalTime.parse(shiftstartStr); 
        this.shiftstart = parsedTime;
                
        String shiftstopStr = shiftParams.get("shiftstop");
        LocalTime parsedTime_two = LocalTime.parse(shiftstopStr);
        this.shiftstop = parsedTime_two;
        
        String lunchstartStr = shiftParams.get("lunchstart");
        LocalTime parsedTime_three = LocalTime.parse(lunchstartStr);
        this.lunchstart = parsedTime_three;
        
        String lunchstopStr = shiftParams.get("lunchstop");
        LocalTime parsedTime_four = LocalTime.parse(lunchstopStr);
        this.lunchstop = parsedTime_four;
        
        this.shiftDuration = (int)ChronoUnit.MINUTES.between(shiftstart, shiftstop);
        this.lunchDuration = (int)ChronoUnit.MINUTES.between(lunchstart, lunchstop);
        
    }
    
    /**
    * <p>These getter methods are used to retrieve the shift rule set information from the database.</p>
    * @return The variables with shift rule set information such as shift id, description, shift start, etc.
    */
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
    
    /**
    * <p>This is a simple setter method for lunch duration which sets a parameter to a variable.</p>
    * @param lunchDuration The parameter passed in to be set to the lunchDuration variable.
    */  
    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }
    
    /**
    * <p>This is a simple setter method for shift duration which sets a parameter to a variable.</p>
    * @param shiftDuration The parameter passed in to be set to the shiftDuration variable.
    */ 
    public void setShiftDuration(int shiftDuration) {
        this.shiftDuration = shiftDuration;
    }
    
    /**
    * <p>This is a simple method which builds and appends a toString() together for the test comparison of a shift rule set. </p>
    * @return the created toString().
    */
    @Override
    public String toString() {

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
