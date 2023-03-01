package edu.jsu.mcis.cs310.tas_sp23.dao;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import java.sql.*;
import java.time.LocalDateTime;
public class PunchDAO {
   private static final String QUERY_FIND = "SELECT * FROM badge WHERE id = ?";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory; 
    }
    
    public Punch find(int id){
        Punch punch = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        String badgeId = rs.getString("badgeid");
                        int terminalId = rs.getInt("terminalid");
                        int eventType = rs.getInt("eventtypeid");
                        LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                     // Using BadgeDAO to find the badge associated with this punch
                        Badge badge = new BadgeDAO(daoFactory).find(badgeId);

                     // Creating a new Punch object using the constructor that takes all the required parameters
                        punch = new Punch(id, terminalId, badge, timestamp, EventType.values()[eventType]);

                  
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

   
    
