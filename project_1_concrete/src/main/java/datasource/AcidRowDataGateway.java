package datasource;

import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcidRowDataGateway {
    long ID;
    String name;
    long solute;

    public static final String existsQuery = "SELECT * FROM acid WHERE name = ?";
    public static final String createQuery = "INSERT INTO acid VALUES (?,?,?)";
    public static final String finderQuery = "SELECT * FROM acid WHERE ID = ?";
    public static final String updateQuery = "UPDATE acid SET name = ?, solute = ? WHERE ID = ?";
    public static final String deleteQuery = "DELETE FROM acid WHERE ID = ?";

    /**
     * Create constructor for new Acid
     * @param ID - ID of new Acid
     * @param name - Name of new Acid
     * @param solute - ID of element that is a solute
     * @throws Exception
     */
    public AcidRowDataGateway(long ID, String name, long solute) throws Exception {
        if(exists(name)){
            throw new AlreadyExistsException("An Acid with this name already exists. Check name and try again!");
        }else{
            try{
                PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
                stmt.setLong(1, ID);
                stmt.setString(2, name);
                stmt.setLong(3, solute);
                stmt.execute();

                this.name = name;
                this.solute = solute;
                this.ID = ID;
            }catch (SQLException UnableToConnect){
                throw new UnableToConnectException("Unable to create Acid. Check connection and try again!");
            }
        }
    }

    /**
     * Finder constructor for Acid
     * @param ID - ID being looked for in the table
     * @throws Exception - throws when the Acid cannot be found
     */
    public AcidRowDataGateway(int ID) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(finderQuery);
            stmt.setLong(1, ID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.ID = ID;
            this.name = rs.getString("name");
            this.solute = rs.getLong("solute");
        }catch (SQLException e){
            throw new EntryNotFoundException("Cannot find Acid with this ID. Check ID and try again");
        }
    }

    /**
     * Updates the database with the current values stored
     * @throws Exception - throws when unable to update the database
     */
    public void persist() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setString(1, name);
            stmt.setLong(2, solute);
            stmt.setLong(3, ID);
            stmt.execute();
        }catch (SQLException e){
            throw new UnableToUpdateException("Unable to update this Acid. Check Connection and try again!");
        }
    }
    /**
     * Deletes an acid from the Acid table
     *
     * @param ID - ID of Acid being deleted
     * @return true - if no exception is thrown
     * @throws Exception - throws when connection to the database cannot be established
     */
    public boolean delete(long ID) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, ID);
            stmt.execute();

        }catch (SQLException unableToDelete){
            throw new UnableToConnectException("Unable to Delete Acid. Check connection and try again!");
        }
        return true;
    }
    /**
     * Checks to see if an Acid with a certain name exists
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
     * sets the name of current Acid to input name
     * @param name - new name for Acid
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets the ID of element this Acid dissolves
     * @param solute - ID of element that is a solute of this acid
     */
    public void setSolute(long solute){
        this.solute = solute;
    }

    /**
     * gets the name of the Acid
     * @return - Name of Acid
     */
    public String getName(){
        return name;
    }

    /**
     * gets the ID of Element that is a solute for this Acid
     * @return - Element ID
     */
    public long getSolute(){
        return solute;
    }
}
