package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class Punch {
   
    private int id; // nullable
    private final int terminalid;
    private final Badge badge;
    private LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp;
    private final EventType punchtype;
    private PunchAdjustmentType adjustmenttype; // nullable
    private LunchStatus adjustedlunchstatus;
    private int graceperiod;
    private int dockpenalty;
    
    
    public int getDockPenalty() {
        return dockpenalty;
        
    }
    public int getGracePeriod() {
        return graceperiod;
    }

    public enum LunchStatus {
        HAPPENING,
        HAPPENED,
        NOT_HAPPENING,
        INAPPLICABLE
    };

    // Constructor for new punches (no ID, original timestamp is current time)
    public Punch(int terminalid, Badge badge, EventType punchtype) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.originaltimestamp = java.time.LocalDateTime.now();
        this.punchtype = punchtype;
    }

    // Constructor for existing punches
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.originaltimestamp = originaltimestamp;
        this.punchtype = punchtype;
    }
    public int getId() {
        return id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public EventType getPunchtype() {
        return punchtype;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }
    
    public PunchAdjustmentType getAdjustmenttype() {
        return adjustmenttype;
    }
    
    public String printAdjusted() {
        //throw new UnsupportedOperationException("Not supported yet.");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateText = originaltimestamp.format(formatter);

        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeText_two = adjustedtimestamp.format(formatter); 
        
        String dayOfWeek = originaltimestamp.getDayOfWeek().toString().substring(0, 3).toUpperCase();

        StringBuilder s = new StringBuilder();        
        s.append("#").append(badge.getId()).append(" ");
        s.append(punchtype).append(": ").append(dayOfWeek).append(" ").append(dateText).append(" ").append(timeText_two).append(" ").append(adjustmenttype);

        return s.toString();
    }
    
    public String printOriginal () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateText = originaltimestamp.format(formatter);

        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeText = originaltimestamp.format(formatter);

        String dayOfWeek = originaltimestamp.getDayOfWeek().toString().substring(0, 3).toUpperCase();

        StringBuilder s = new StringBuilder();        
        s.append("#").append(badge.getId()).append(" ");
        s.append(punchtype).append(": ").append(dayOfWeek).append(" ").append(dateText).append(" ").append(timeText);

        return s.toString();
    }
    
    public void adjust(Shift s) {

        if(originaltimestamp.getDayOfWeek() == DayOfWeek.SATURDAY || originaltimestamp.getDayOfWeek() == DayOfWeek.SUNDAY ){
           adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
           adjustedlunchstatus = LunchStatus.INAPPLICABLE;
        }
        else {
            switch(punchtype){
                case CLOCK_IN:
                    
                    break;
                case CLOCK_OUT:
                    break;
            }
        }
    }    
}
