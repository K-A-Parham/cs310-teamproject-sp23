package edu.jsu.mcis.cs310.tas_sp23;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* <p>An Employee class that represents employees.</p>
* @author Cayden Tucker and Makiya Kirby
*/
public class Employee {
    
    /**
    *
    * Instance fields used to hold information about employees such as their id, full name, active status, badge, department, etc.
    */
    private final int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final LocalDateTime active;
    private final Badge badge;
    private final Department department;
    private final Shift shift;
    private final EmployeeType employeeType;
    
    /**
    * <p>This is a constructor for employee information. </p>
    * @param id the employee's id.
    * @param firstname the employee's first name.
    * @param middlename the employee's middle name.
    * @param lastname the employee's last name.
    * @param active the employee's active status.
    * @param badge the employee's badge.
    * @param department the employee's department.
    * @param shift the employee's shift.
    * @param employeeType the employee type.
    */
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
    
    /**
    * <p>These getter methods are used to retrieve employee information from the database.</p>
    * @return The variables with employee information such as badge id, first name, last name, department, etc.
    */
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
    
    /**
    * <p>This is a simple method which builds and appends information on employees to a toString(). </p>
    * @return the created toString().
    */
    @Override
    public String toString(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = active.format(formatter);
        
        StringBuilder stringbuilder = new StringBuilder();
         
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
