package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.ArrayList;
import java.util.TimeZone;
public class PunchDAO {

    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";
    private static final String QUERY_CREATE = "INSERT INTO event (terminalid, badgeid, timestamp, eventtypeid) VALUES(?, ?, ?, ?)";
    private static final String QUERY_LIST = "SELECT *, DATE(`timestamp`) AS tsdate FROM event WHERE badgeid = ? HAVING tsdate = ? ORDER BY 'timestamp';";
    private static final String QUERY_LIST_DAY = "SELECT *, DATE(`timestamp`) AS tsdate FROM event WHERE badgeid = ? HAVING tsdate > ? ORDER BY 'timestamp' LIMIT 1;";
   
    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
  
    

    
    public Punch find(int id){
        Punch punch = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
         LocalDateTime originalTimeStamp;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        //Create badge variable

                        BadgeDAO badgeDAO = new BadgeDAO(daoFactory);
                        String badgeId = rs.getString("badgeid");
                        Badge badge = badgeDAO.find(badgeId);

                        //Get eventtype and punchtype

                        int eventtypeid = rs.getInt("eventtypeid");
                        EventType punchtype = EventType.values()[eventtypeid];

                        int terminalid = rs.getInt("terminalid");

                        System.out.println(ZoneId.systemDefault());
                        System.out.println(TimeZone.getDefault());
                        originalTimeStamp = rs.getTimestamp("timestamp").toLocalDateTime();
                        punch = new Punch(id, terminalid, badge, originalTimeStamp, punchtype);
                        
                        
                        
                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return punch;

    }
    
     public ArrayList list (Badge badge, LocalDate day){

        ArrayList<Punch> punches = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();    

            if (conn.isValid(0)) {                
                
                ps = conn.prepareStatement(QUERY_LIST);
                ps.setString(1, badge.getId()); 
                ps.setDate(2, java.sql.Date.valueOf(day));

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();
                    
                    PunchDAO punchDAO = new PunchDAO(daoFactory);
                    
                    while (rs.next()) {
                        
                       int id = rs.getInt("id");                       
                       Punch punch = punchDAO.find(id);                      
                       punches.add(punch);
                    }
                    
                    //closing pair checking.
                    
                    int lastIndex = punches.size();                    
                    Punch lastPunchIndex = punches.get(lastIndex - 1);
                    
                    EventType lastPunch = lastPunchIndex.getPunchtype();

                    if (lastPunch == EventType.CLOCK_IN) {
                        
                        ps = conn.prepareStatement(QUERY_LIST_DAY);
                        
                        //Check one day past previous range. 

                        ps.setString(1, badge.getId()); 
                        ps.setDate(2, java.sql.Date.valueOf(day)); 
                        
                        boolean hasresults2 = ps.execute();
                        
                        if (hasresults2) {
                            
                            rs = ps.getResultSet();
                            
                            while (rs.next()) {
                                
                                //Punch type of the next day.
                                
                                int id = rs.getInt("id");
                                Punch firstPunchDay3 = punchDAO.find(id);                               
                                EventType firstPunchOfDay = firstPunchDay3.getPunchtype();
                                
                                if ((firstPunchOfDay == EventType.CLOCK_OUT) || (firstPunchOfDay == EventType.TIME_OUT)) {
                                    punches.add(firstPunchDay3);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }
        return punches;
    }

   public ArrayList<Punch> list(Badge badge, LocalDate begin, LocalDate end) {
      
        ArrayList<Punch> list = new ArrayList();
        
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        return null;
        
        
        
   }
    public int create(Punch punch){
        int key = 0;
        ResultSet rs = null;
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        Employee employee = employeeDAO.find(punch.getBadge());

       if (punch.getTerminalid() == employee.getDepartment().getTerminalId() || punch.getTerminalid() == 0) {
           PreparedStatement ps = null;
            try {
                Connection conn = daoFactory.getConnection();
                if (conn.isValid(0)) {
                  String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(punch.getOriginaltimestamp());
                  int eventTypeId = EventType.valueOf(punch.getPunchtype().name()).ordinal();

                    ps = conn.prepareStatement(QUERY_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, punch.getTerminalid());
                    ps.setString(2, punch.getBadge().getId());
                    ps.setString(3, date);
                    ps.setInt(4, eventTypeId);

                    int result = ps.executeUpdate();
                    if (result > 0) {

                        rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            key = rs.getInt(1);
                        }

                    }
                }
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
       }
        
    return key;
}
    

}
