package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Shift;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ShiftDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";
    private static final String QUERY_FIND_BY_BADGE = "SELECT * FROM employee WHERE badgeid = ?";

    private final DAOFactory daoFactory;
    
    ShiftDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
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

                    } //description, shiftstart, shiftstop, shiftDuration, lunchstart, lunchstop, lunchDuration
                    
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
}
