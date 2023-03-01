/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Punch {
   
    private Integer id; // nullable
    private int terminalid;
    private Badge badge;
    private LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp; // nullable
    private EventType punchtype;
    private PunchAdjustmentType adjustmenttype; // nullable

    // Constructor for new punches (no ID, original timestamp is current time)
    public Punch(int terminalid, Badge badge, EventType punchtype) {
        this(null, terminalid, badge, LocalDateTime.now(), punchtype);
    }

    // Constructor for existing punches
    public Punch(Integer id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
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
    
    public LocalDateTime getadjustedtimestamp(){
        return adjustedtimestamp;
    }
    
    public PunchAdjustmentType getadjustmenttype(){
        PunchAdjustmentType PunchAdjustmentTypeadjustmenttype = null;
        return PunchAdjustmentTypeadjustmenttype;
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

    public void setAdjustedTimestamp(LocalDateTime toLocalDateTime) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setAdjustmentType(PunchAdjustmentType valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
} 
