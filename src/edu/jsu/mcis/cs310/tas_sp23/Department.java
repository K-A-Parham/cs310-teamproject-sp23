
package edu.jsu.mcis.cs310.tas_sp23;

public class Department {
    private final int id;
    private final String description;
    private final int terminalid;
    
    public Department (int id, int terminalid, String description){
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
        StringBuilder w = new StringBuilder();
        
        w.append('#').append(id).append(' ');
        w.append('(').append(description).append(')');
        w.append(", Terminal ID: ").append(terminalid);
        return w.toString();
    }
}
