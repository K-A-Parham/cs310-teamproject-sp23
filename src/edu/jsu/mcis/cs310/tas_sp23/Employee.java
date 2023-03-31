package edu.jsu.mcis.cs310.tas_sp23;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private LocalDateTime active;
    private Badge badge;
    private Department department;
    private String Shift;
    private String EmployeeType;
    
    public Employee(int id, String firstname, String middlename, String lastname, 
            LocalDateTime active, Badge badge, Department department, String Shift, String EmployeeType)
    {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.active = active;
        this.badge = badge;
        this.department = department;
        this.Shift = Shift;
        this.EmployeeType = EmployeeType;
    }

    public Employee(int id, String firstName, String middleName, String lastName, LocalDateTime active, Badge badge, Department department, Shift shift, EmployeeType employeeType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
         
         
         return stringbuilder.toString();
    }
}
