import java.sql.*;


public class JDBC {

    public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu/swe400_21"; //jdbc:mysql://db.engr.ship.edu:3306/csc471_05?useTimezone=true&serverTimezone=UTC
    public static final String LOGIN_NAME = "swe400_2";
    public static final String PASSWORD = "pwd4swe400_2F22";
    protected Connection m_dbConn = null;

    public JDBC() {

    }

    /**
     * ensures JDBC is functional
     *
     * @return - whether JDBC is functional or not
     * @throws - SQLException
     */
    public boolean activateJDBC() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);

        Statement stmt = m_dbConn.createStatement();
        String test = "SELECT * FROM test";
        stmt.execute(test);

        return true;
    }
}