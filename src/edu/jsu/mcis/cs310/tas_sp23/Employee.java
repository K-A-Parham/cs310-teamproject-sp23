package edu.jsu.mcis.cs310.tas_sp23;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {
    private final int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final LocalDateTime active;
    private final Badge badge;
    private final Department department;
    private final Shift shift;
    private final EmployeeType employeeType;
    
    public Employee(int id, String firstname, String middlename, String lastname, 
            LocalDateTime active, Badge badge, Department department, Shift shift, EmployeeType employeeType)
    {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.active = active;
        this.badge = badge;
        this.department = department;
        this.shift = shift;
        this.employeeType = employeeType;
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
    public Department getDepartment(){
        return department;
    }
    public Shift getShift(){
        return shift;
    }
    public EmployeeType getEmployeeType(){
        return employeeType;
    }
    
    @Override
    public String toString(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = active.format(formatter);
        
        StringBuilder stringbuilder = new StringBuilder();
         
        
        //"ID #14: Donaldson, Kathleen C (#229324A4), Type: Full-Time, Department: Press, Active: 02/02/2017"
        stringbuilder.append("ID ").append("#").append(id).append(':');
        stringbuilder.append(' ').append(lastname).append(',');
        stringbuilder.append(' ').append(firstname).append(' ');
        stringbuilder.append(middlename).append(' ');
        stringbuilder.append("(#").append(badge.getId()).append("), ");
        stringbuilder.append("Type: ").append(employeeType).append(',').append(' ');
        stringbuilder.append("Department: ").append(department.getDescription()).append(',');
        stringbuilder.append(" Active: ").append(date);
         
         
         return stringbuilder.toString();
    }
}
