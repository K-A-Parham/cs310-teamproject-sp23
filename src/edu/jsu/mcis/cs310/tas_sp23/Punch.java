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
    private PunchAdjustmentType adjustmenttype;
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
        before_shiftstart = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStart()).minus(Duration.of(s.getRoundInterval(), ChronoUnit.MINUTES));
        after_shiftstop = LocalDateTime.of(originaltimestamp.toLocalDate(), s.getShiftStop()).plus(Duration.of(s.getRoundInterval(), ChronoUnit.MINUTES));

        adjustmenttype = null;
        adjustedtimestamp = originaltimestamp;
        
        DayOfWeek dayofweek = originaltimestamp.getDayOfWeek();
        
        if ( (dayofweek != DayOfWeek.SATURDAY) && (dayofweek != DayOfWeek.SUNDAY)) {

            if (punchtype == EventType.CLOCK_IN) {

                //shift clock in
                if (originaltimestamp.isBefore(shiftstart) && (originaltimestamp.isAfter(before_shiftstart) || originaltimestamp.isEqual(before_shiftstart)))
                {
                    adjustedtimestamp = shiftstart;
                    
                    adjustmenttype = PunchAdjustmentType.SHIFT_START;
                }
                else if (originaltimestamp.isAfter(shiftstart) && originaltimestamp.isBefore(graceperiod))
                {
                    adjustedtimestamp = shiftstart;
                    adjustmenttype = PunchAdjustmentType.SHIFT_START;
                }
                else if (originaltimestamp.isAfter(graceperiod) && (originaltimestamp.isBefore(dockpenalty) || originaltimestamp.isEqual(dockpenalty)))
                {
                    adjustedtimestamp = dockpenalty;
                    adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
                }

                //lunch clock in: might not be correct
                else if ((originaltimestamp.isAfter(lunchstart) || originaltimestamp.isEqual(lunchstart)) && originaltimestamp.isBefore(lunchstop))
                {
                    adjustedtimestamp = lunchstop;
                    adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
                }

            }

            else if (punchtype == EventType.CLOCK_OUT) {

                //lunch clock out: might not be correct
                if (originaltimestamp.isAfter(lunchstart) && (originaltimestamp.isBefore(lunchstop) || originaltimestamp.isEqual(lunchstop)))
                {
                    adjustedtimestamp = lunchstart;
                    adjustmenttype = PunchAdjustmentType.LUNCH_START;
                }

                //shift clock out
                else if (originaltimestamp.isAfter(shiftstop) && (originaltimestamp.isBefore(after_shiftstop) || originaltimestamp.isEqual(after_shiftstop)))  //might need to add an isBefore method for the time after the end of the shift
                {
                    adjustedtimestamp = shiftstop;
                    adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
                }

                else if (originaltimestamp.isBefore(shiftstop) && originaltimestamp.isAfter(clockout_graceperiod))
                {
                    adjustedtimestamp = shiftstop;
                    adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
                }

                else if (originaltimestamp.isBefore(clockout_graceperiod) && (originaltimestamp.isAfter(clockout_dockpenalty) || originaltimestamp.isEqual(clockout_dockpenalty)))
                {
                    adjustedtimestamp = clockout_dockpenalty;
                    adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
                }

            }
            
        }

        // Has punch already been adjusted?  If so, no need to check more rules

        if (adjustmenttype == null) {

            // round up/down to nearest interval increment?

            int minute = originaltimestamp.getMinute();
            int interval = s.getRoundInterval();

            if (minute % interval != 0) {

                int adjustedminute = 0;

                if ( (minute % interval) < (interval / 2)) {
                    adjustedminute = (Math.round(minute / interval) * interval);
                }
                else {
                    adjustedminute = (Math.round(minute / interval) * interval) + interval;
                }

                adjustedtimestamp = adjustedtimestamp.plusMinutes(adjustedminute - minute);
                adjustedtimestamp = adjustedtimestamp.withSecond(0).withNano(0);

                adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;

            }
            else {
                adjustedtimestamp = adjustedtimestamp.withSecond(0).withNano(0);
                adjustmenttype = PunchAdjustmentType.NONE;
            }


        }
    
    }
    
}
