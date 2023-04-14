package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Punch {
   
    private int id; // nullable
    private final int terminalid;
    private final Badge badge;
    private final LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp;
    private final EventType punchtype;
    private PunchAdjustmentType adjustmenttype; // nullable
    private LunchStatus adjustedlunchstatus;
    
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
        s.append(punchtype).append(": ").append(dayOfWeek).append(" ").append(dateText).append(" ").append(timeText_two).append(" (").append(adjustmenttype).append(")");

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
    
    //still working on it (unfinished)
    public void adjust(Shift s) {
        
    LocalDateTime shiftstart, before_shiftstart, graceperiod, dockpenalty, lunchstart, lunchstop;
    LocalDateTime shiftstop, after_shiftstop, clockout_graceperiod, clockout_dockpenalty;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    shiftstart = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStart());
    graceperiod = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStart()).plus(Duration.of(s.getGracePeriod(), ChronoUnit.MINUTES));
    dockpenalty = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStart()).plus(Duration.of(s.getDockPenalty(), ChronoUnit.MINUTES));
    lunchstart = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getLunchStart());
    lunchstop = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getLunchStop());
    shiftstop = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStop());
    clockout_graceperiod = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStop()).minus(Duration.of(s.getGracePeriod(), ChronoUnit.MINUTES));
    clockout_dockpenalty = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStop()).minus(Duration.of(s.getDockPenalty(), ChronoUnit.MINUTES));
    
    //shift clock in
    if (originaltimestamp.isBefore(shiftstart))  //might need to add an isAfter method for the time before the end of the shift
    {
        adjustedtimestamp = shiftstart;
        adjustmenttype = PunchAdjustmentType.SHIFT_START;
    }
    else if (originaltimestamp.isAfter(shiftstart) && originaltimestamp.isBefore(graceperiod))
    {
        adjustedtimestamp = shiftstart;
        adjustmenttype = PunchAdjustmentType.SHIFT_START;
    }
    else if (originaltimestamp.isAfter(graceperiod) && originaltimestamp.isBefore(dockpenalty))
    {
        adjustedtimestamp = dockpenalty;
        adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
    }
    //lunch clock out: might not be correct
    else if (originaltimestamp.isAfter(lunchstart) && originaltimestamp.isBefore(lunchstop))
    {
        adjustedtimestamp = lunchstart;
        adjustmenttype = PunchAdjustmentType.LUNCH_START;
    }
    //lunch clock in: might not be correct
    else if (originaltimestamp.isAfter(lunchstart) && originaltimestamp.isBefore(lunchstop))
    {
        adjustedtimestamp = lunchstop;
        adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
    }
    //shift clock out
    if (originaltimestamp.isAfter(shiftstop))  //might need to add an isBefore method for the time after the end of the shift
    {
        adjustedtimestamp = shiftstop;
        adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
    }
    else if (originaltimestamp.isBefore(shiftstop) && originaltimestamp.isAfter(clockout_graceperiod))
    {
        adjustedtimestamp = shiftstop;
        adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
    }
    else if (originaltimestamp.isBefore(clockout_graceperiod) && originaltimestamp.isAfter(clockout_dockpenalty))
    {
        adjustedtimestamp = clockout_dockpenalty;
        adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
    }
    
    //still need to add "Interval Round" and "None" will try to over the weekend
    
/*
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
*/

    }    
}
