import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.PunchAdjustmentType;
import java.sql.*;
import java.time.LocalDateTime;

public class PunchDAO {
    private final Connection conn;

    public PunchDAO(Connection conn) {
        this.conn = conn;
    }

    public Punch find(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM event WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int terminalid = rs.getInt("terminalid");
                Badge badge = new Badge(rs.getString("badgeid"));
                LocalDateTime originaltimestamp = rs.getTimestamp("originaltimestamp").toLocalDateTime();
                EventType punchtype = EventType.valueOf(rs.getString("eventtypeid"));

                Punch punch = new Punch(id, terminalid, badge, originaltimestamp, punchtype);
                punch.setAdjustedTimestamp(rs.getTimestamp("adjustedtimestamp").toLocalDateTime());
                punch.setAdjustmentType(PunchAdjustmentType.valueOf(rs.getString("adjustmenttype")));
                return punch;
            } else {
                return null;
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
             
            }
        }
    }
}