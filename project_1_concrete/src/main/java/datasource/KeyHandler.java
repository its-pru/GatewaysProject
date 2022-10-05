package datasource;

import exceptions.UnableToGetKeyException;
import exceptions.UnableToUpdateKeyException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KeyHandler {

    public static final String getCurKey = "SELECT * FROM key";
    public static final String updateKey = "UPDATE key VALUE key = ? WHERE key = ?";

    public KeyHandler(){}

    public static long getNewKey() throws Exception {
        long curKey = getCurrentKey();
        long newKey = curKey + 1;

        try{
            PreparedStatement stmt = datasource.JDBC.getJDBC().getConnect().prepareStatement(updateKey);
            stmt.setLong(1, newKey);
            stmt.setLong(2, curKey);
            stmt.execute();
        }catch (SQLException e){
            throw new UnableToUpdateKeyException("Unable to get new Key. Check connection and try again!");
        }

        return newKey;
    }

    private static long getCurrentKey() throws Exception{
        try{
            Statement stmt = datasource.JDBC.getJDBC().getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(getCurKey);
            rs.next();
            return rs.getLong("key");
        }catch (SQLException e){
            throw new UnableToGetKeyException("Unable to get current Key. Check connection and try again!");
        }
    }
}
