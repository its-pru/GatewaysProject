package datasource;

import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseRowDataGateway {

    long ID;
    String name;
    long solute;

    private static final String createString = "INSERT INTO base VALUES (?,?,?)";
    private static final String existsQuery = "SELECT * FROM base WHERE name = ?";
    private static final String finderQuery = "SELECT * FROM base WHERE ID = ?";
    private static final String updateQuery = "UPDATE base SET name = ?, solute = ? WHERE ID = ?";
    private static final String deleteQuery = "DELETE FROM base WHERE ID = ?";

    /**
     * Create constructor for BaseRowDataGateway
     * @param name - name of base
     * @param solute - id of solute for base
     * @Exception - Throws when SQL can't Connect
     */
    public BaseRowDataGateway(long ID, String name, long solute) throws Exception {
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createString);
            stmt.setLong(1, ID);
            stmt.setString(2, name);
            stmt.setLong(3, solute);
            stmt.execute();
        }catch (SQLException unableToConnect){
            throw new UnableToConnectException("Unable to create new Base. Check connection and try again!");
        }
    }

    public BaseRowDataGateway(long ID) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(finderQuery);
            stmt.setLong(1, ID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.ID = ID;
            this.name = rs.getString("name");
            this.solute = rs.getLong("solute");
        }catch(SQLException notFound){
            throw new EntryNotFoundException("Could not find an entry with this ID. Check ID and try again");
        }
    }


    /**
     * Checks to see if a Compound with a certain name exists
     * @param name - name being searched for
     * @return - boolean whether something exists or not
     * @throws Exception - throws when unable to connect to DB
     */
    public boolean exists(String name) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        }catch(SQLException unableToConnect){
            throw new UnableToConnectException("Unable to check if Compound exists. Check Connection and try again!");
        }
    }
}
