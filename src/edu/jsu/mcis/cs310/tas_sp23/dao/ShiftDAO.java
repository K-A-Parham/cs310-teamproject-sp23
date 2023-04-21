package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Shift;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
* <p>A ShiftDAO class that creates and populates the model objects from the Shift class and retrieves data from the database.</p>
* @author Makiya Kirby
*/
public class ShiftDAO {
    
    /**
    *
    * Fields/queries used to get necessary information in the database.
    */
    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";
    private static final String QUERY_FIND_BY_BADGE = "SELECT * FROM employee WHERE badgeid = ?";

    private final DAOFactory daoFactory;
    
    ShiftDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
    /**
    * <p>This is a find method that finds a shift rule set based on its numeric id.</p>
    * @param id The id used to find a shift rule set.
    * @return The shift rule set based on the numeric id.
    */
    public Shift find(int id) {
        
        Shift shift = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);
                
                boolean hasresults = ps.execute();

                if (hasresults) {
                    
                    ArrayList<String> fields = new ArrayList<>();

                    rs = ps.getResultSet();
                    ResultSetMetaData meta = rs.getMetaData();
                    
                    int cols = meta.getColumnCount();
                    
                    for (int k = 1; k <= cols; ++k) {
                        fields.add(meta.getColumnName(k));
                    }
                    
                    if (rs.next()) {
                        
                        HashMap<String, String> params = new HashMap<>();

                        for (String field : fields) {
                            
                            params.put(field, rs.getString(field));
                            
                        }
                        
                        // continue for other fields
                        
                        shift = new Shift(params);

                    }
                    
                }
                
                
            }
            
        }
        catch (SQLException e) {

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
        
        return shift;
        
    }
    
    /**
    * <p>This is a find method that accepts an employee badge object and looks up the employee's assigned shift in the database.</p>
    * @param badge The badge argument that the method accepts and uses to find an assigned shift.
    * @return The shift rule set based on the employee badge.
    */
    public Shift find(Badge badge) {
        
        Shift shift = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_BY_BADGE);
                ps.setString(1, badge.getId());

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        int shiftid = rs.getInt("shiftid");
                        shift = find(shiftid);
                        

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
        
        
        return shift; 
    }
    public Shift find(Badge b, LocalDate ts) {
        
        return null;
        
    }
}
/*public Shift find(Badge b, LocalDate ts) {
        
        return null;
        
    }
*/