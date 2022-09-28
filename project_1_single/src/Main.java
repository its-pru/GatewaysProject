import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        JDBC sql = new JDBC();
        try {
            sql.activateJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}