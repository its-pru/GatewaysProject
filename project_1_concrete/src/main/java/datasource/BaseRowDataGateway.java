package datasource;

import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

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

    /**
     * Finder Constructor for BaseRowDataGateway
     * @param ID - ID of Base
     * @throws Exception - throws when unable to find Base
     */
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
     * Updates the database with the current values of this base
     * @throws Exception - throws when unable to update in database
     */
    public void persist() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setString(1, name);
            stmt.setLong(2,solute);
            stmt.setLong(3, ID);
            stmt.execute();
        }catch (SQLException cannotUpdate){
            throw new UnableToUpdateException("Unable to update this Base. Check connection and try again");
        }
    }

    /**
     * Deletes a base from the database
     * @param ID - ID of base being deleted
     * @throws Exception - throws when unable to delete
     */
    public void delete(long ID) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, ID);
            stmt.execute();
        }catch (SQLException e){
            throw new UnableToConnectException("Unable to delete Base. Check connection and try again!");
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

    /**
     * sets the name of current base to input name
     * @param name - new name for this base
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets the ID of element that the  base dissolves
     * @param solute - ID of element that is a solute of this base
     *
     */
    public void setSolute(long solute){
        this.solute = solute;
    }

    /**
     * gets the name of the current base
     * @return - Name of the base
     */
    public String getName(){
        return name;
    }

    /**
     * gets the ID of Element that is a solute for this base
     * @return - Element ID
     */
    public long getSolute(){
        return solute;
    }
}
