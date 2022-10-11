package datasource;

import exceptions.UnableToGetKeyException;
import exceptions.UnableToUpdateKeyException;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KeyHandler {

    public static final String getCurKey = "SELECT * FROM keyHandler";
    public static final String updateKey1 = "INSERT INTO keyHandler VALUES (?)";
    public static final String updateKey2 = "DELETE FROM keyHandler WHERE currentKey = ?";

    public KeyHandler(){}

    /**
     * gets the next available key
     * @return - next available key
     * @throws Exception - throws when unable to connect to Database
     */
    public static long getNewKey() throws Exception {
        long curKey = getCurrentKey();
        long newKey = curKey + 1;

        try{
            PreparedStatement stmt = datasource.JDBC.getJDBC().getConnect().prepareStatement(updateKey1);
            stmt.setLong(1, newKey);
            stmt.execute();

            PreparedStatement stmt2 = JDBC.getJDBC().getConnect().prepareStatement(updateKey2);
            stmt2.setLong(1,curKey);
            stmt2.execute();

        }catch (SQLException e){
            e.printStackTrace();
            throw new UnableToUpdateKeyException("Unable to get new Key. Check connection and try again!");
        }

        return newKey;
    }

    /**
     * gets the current key used
     * @return - current key
     * @throws Exception - throws when unable to connect to Database
     */
    public static long getCurrentKey() throws Exception{
        try{
            Statement stmt = JDBC.getJDBC().getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(getCurKey);
            rs.next();
            return rs.getLong("currentKey");
        }catch (SQLException e){
            e.printStackTrace();
            throw new UnableToGetKeyException("Unable to get current Key. Check connection and try again!");
        }
    }
}
