/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;


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
    public void Punch(Integer id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.originaltimestamp = originaltimestamp;
        this.punchtype = punchtype;
    }
} 
