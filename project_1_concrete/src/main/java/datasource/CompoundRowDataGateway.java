package datasource;

import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompoundRowDataGateway {

    private long ID;
    private String name;

    private static final String createQuery = "INSERT INTO compound VALUE (?,?)";
    private static final String findQuery = "SELECT * FROM compound WHERE ID = ?";
    private static final String updateQuery = "UPDATE compound SET name = ? WHERE ID = ?";
    private static final String deleteQuery = "DELETE FROM compound WHERE ID = ?";
    private static final String existsQuery = "SELECT * FROM compound WHERE name = ?";
    /**
     * Create constructor for new Compound
     * @param ID - ID of new Compound
     * @param name - Name of new Compound
     */
    public CompoundRowDataGateway(long ID, String name) throws Exception{
        this.ID = ID;
        this.name = name;

        if(exists(name)){
            throw new AlreadyExistsException("A compound with this name already exists. Check value and try again");
        }else {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
            stmt.setLong(1, ID);
            stmt.setString(2, name);
            stmt.execute();
        }
    }

    /**
     * Finder constructor for a new compound
     * @param ID - ID of compound being searched for
     */
    public CompoundRowDataGateway(long ID) throws Exception{
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(findQuery);
            stmt.setLong(1, ID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.ID = ID;
            this.name = rs.getString("name");
        }catch (SQLException unableToFind){
            throw new EntryNotFoundException("Unable to find compound with this ID. Check ID and try again!");
        }

    }

    /**
     * Updates Database with current values for current compound
     * @throws Exception
     */
    public void persists() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setString(1 ,name);
            stmt.setLong(2, ID);
            stmt.execute();
        }catch (SQLException unableToUpdate){
            throw new UnableToUpdateException("Unable to update. Check Network connection and try again");
        }
    }

    /**
     * Deletes entry with the current elements ID
     * @throws Exception - Throws when SQL is unable to connect
     */
    public void delete() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, ID);
            stmt.execute();
        }catch(SQLException unableToDelete){
            throw new UnableToConnectException("Unable to delete element. Check Connection any try again");
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
     * gets the name of the current compound
     * @return - name of compound
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the current compound
     * @param name - name current compound is being set to
     */
    public void setName(String name){
        this.name = name;
    }
}
