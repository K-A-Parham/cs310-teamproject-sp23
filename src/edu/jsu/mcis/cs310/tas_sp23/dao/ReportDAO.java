package edu.jsu.mcis.cs310.tas_sp23.dao;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Department;
import edu.jsu.mcis.cs310.tas_sp23.Employee;
import edu.jsu.mcis.cs310.tas_sp23.EmployeeType;
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.dao.DAOFactory;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
/**
 *
 * @author quayf
 */
public class ReportDAO {

    private static final String QUERY = "SELECT * FROM employee WHERE departmentid = ? ORDER BY lastname, firstname, middlename";
    private static final String QUERY2 = "SELECT * FROM employee ORDER BY lastname, firstname, middlename";

    private final DAOFactory daoFactory;

    
     ReportDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public String getBadgeSummary(Object id) {
        String s = "";

        JsonArray jsonArray = new JsonArray();

       
        PreparedStatement ps = null;
       
        ResultSet rs = null;
        
        boolean hasresults;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                if (id == null) {
                    ps = conn.prepareStatement(QUERY2);
                    hasresults = ps.execute();
                } else {
                    ps = conn.prepareStatement(QUERY);
                    ps.setInt(1, Integer.parseInt(id.toString()));
                    hasresults = ps.execute();
                }
     
     
     
     
}
}