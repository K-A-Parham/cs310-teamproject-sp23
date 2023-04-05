package edu.jsu.mcis.cs310.tas_sp23;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Absenteeism {
    
    private final Employee employee;
    private final LocalDate payPeriod;
    private final BigDecimal bigDec;
    
    public Absenteeism(Employee employee, LocalDate localDate, BigDecimal bigDec){
        this.employee = employee;
        this.payPeriod = localDate;
        this.bigDec = bigDec;
    }
    
    public Employee getEmployee(){
        return employee;
    }
    public LocalDate getPayPeriod(){
        return payPeriod;
    }
    public BigDecimal getBigDec() {
        return bigDec;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        String date = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(payPeriod);
        
        s.append("#").append(employee.getBadge().getId());
        s.append(" (Pay Period ").append(date).append("): ");
        s.append(bigDec).append("%");
        
        return s.toString();
    }
}
