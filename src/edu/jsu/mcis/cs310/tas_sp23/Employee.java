
package edu.jsu.mcis.cs310.tas_sp23;
import edu.jsu.mcis.cs310.tas_sp23.Badge; 
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import java.sql.*;
import java.time.LocalDateTime;


public class Employee { 
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private LocalDateTime active;
    private Badge badge;
    private String Department;
    private String Shift;
    private String EmployeeType;
    
    public Employee(int id, String firstname, String middlename, String lastname, 
            LocalDateTime active, Badge badge, String Department, String Shift, String EmployeeType)
    {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.active = active;
        this.badge = badge;
        this.Department = Department;
        this.Shift = Shift;
        this.EmployeeType = EmployeeType;
    }
    
    public int getId(){
        return id;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public String getLastname(){
        return lastname;
    }
    public LocalDateTime getActive(){
        return active;
    }
    public Badge getBadge(){
        return badge;
    }
    public String getDepartment(){
        return Department;
    }
    public String getShift(){
        return Shift;
    }
    public String getEmployeeType(){
        return EmployeeType;
    }
    
    @Override
    public String toString(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = active.format(formatter);
        
         StringBuilder stringbuilder = new StringBuilder();
         
         stringbuilder.append('#').append(id).append(' ');
         stringbuilder.append('(').append(firstname).append(')');
         stringbuilder.append('(').append(middlename).append(')');
         stringbuilder.append('(').append(lastname).append(')');
         stringbuilder.append(" (").append(badge).append(") ");
         stringbuilder.append("Type: ").append(Shift).append(" ");
         stringbuilder.append("Department: ").append(Department).append(" ");
         stringbuilder.append("Active: ").append(active).append(" ");
         
         return stringbuilder.toString();
    }
}

