
package edu.jsu.mcis.cs310.tas_sp23;

public class Department {
    private final int id;
    private final String description;
    private final int terminalid;
    
    public Department (int id, String description, int terminalid){
        this.id = id;
        this.terminalid = terminalid;
        this.description = description;
       
    }

  
    
    public int getId(){
        return id;
    }
    public int getTerminalId(){
        return terminalid;
    }
    public String getDescription(){
        return description;
    }
    
    @Override
    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();
        
        stringbuilder.append('#').append(id).append(' ');
        stringbuilder.append('(').append(description).append(')');
        stringbuilder.append(", Terminal ID: ").append(terminalid);
        return stringbuilder.toString();
    }
}
