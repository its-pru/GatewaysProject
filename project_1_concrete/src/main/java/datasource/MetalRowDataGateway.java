package datasource;

import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToCreateException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetalRowDataGateway{

    //Instance variables for each Metal
    long ID;
    String name;
    long atomicNumber;
    double atomicMass;
    long dissolvedBy;

    private static final String MetalCreateString = "INSERT INTO metal VALUES (?,?,?,?,?)";
    private static final String existsString= "SELECT * FROM metal WHERE name = ? OR atomicNumber = ? OR atomicMass = ?";
    private static final String finderString = " SELECT * FROM metal WHERE name = ? OR atomicNumber = ? OR atomicMass = ?";
    private static final String updateString = "UPDATE metal SET name = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ? WHERE ID = ?";
    private static final String deleteString = "DELETE FROM metal WHERE ID = ?";

    /**
     * Create constructor for a new Metal
     * @param ID - ID of the new Metal
     * @param name - Name of the new Metal
     * @param atomicNumber - Atomic number of new Metal
     * @param atomicMass - Atomic mass of new Metal
     * @throws Exception - throws when class is unable to connect ot database
     */
    public MetalRowDataGateway(long ID, String name, long atomicNumber, double atomicMass, long dissolvedBy) throws Exception{
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.dissolvedBy = dissolvedBy;

        if(exists(name, atomicNumber, atomicMass)){
            throw new UnableToCreateException("There is already an Metal for these parameters. Change them and try again");
        }else {
            try {
                PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(MetalCreateString);
                stmt.setLong(1, ID);
                stmt.setString(2, name);
                stmt.setLong(3, atomicNumber);
                stmt.setDouble(4, atomicMass);
                stmt.setLong(5, dissolvedBy);
                stmt.execute();
            } catch (SQLException unableToCreate) {
                throw new UnableToCreateException("Unable to create Metal. Check inputs and try again");
            }
        }
    }

    /**
     * Finder constructor for Metal
     * @param ID - ID of Metal being searched for
     * @throws Exception
     */
    public MetalRowDataGateway(long ID) throws Exception{
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(finderString);
            stmt.setLong(1, ID);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            this.ID = ID;
            this.name = rs.getString("name");
            this.atomicMass = rs.getDouble("atomicMass");
            this.atomicNumber = rs.getLong("atomicNumber");

        }catch (SQLException notFound){
            throw new EntryNotFoundException("Metal for this ID not found. Check ID and try again");
        }
    }

    /**
     * Updates row in database with current values held
     * @throws Exception - throws when persist fails to update the Metal
     */
    public void persist() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateString);
            stmt.setString(1, name);
            stmt.setLong(2, atomicNumber);
            stmt.setDouble(3, atomicMass);
            stmt.setLong(4, dissolvedBy);
            stmt.execute();
        }catch (SQLException e){
            throw new UnableToUpdateException("Unable to update Metal. Check network connection and try again!");
        }
    }

    /**
     * deletes a Metal from the Database
     *
     * @return
     * @throws Exception - throws when Metal being deleted isn't found
     */
    public boolean delete(long ID)throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteString);
            stmt.setLong(1, ID);
            stmt.execute();
        }catch (SQLException e){
            throw new EntryNotFoundException("Could not find Metal with this ID. Check ID and try again");
        }
        return true;
    }

    /**
     * Checks to see if a Metal exists for a set of parameters
     * @param name - name being searched for
     * @param atomicNumber - atomic number being searched for
     * @param atomicMass - atomic mass being searched for
     * @return - True if exists, False if not
     * @throws UnableToConnectException
     */
    public boolean exists(String name, long atomicNumber, double atomicMass) throws UnableToConnectException {
        PreparedStatement exist;
        try{
            exist = JDBC.getJDBC().getConnect().prepareStatement(existsString);
            exist.setString(1, name);
            exist.setLong(2, atomicNumber);
            exist.setDouble(3, atomicMass);
            exist.execute();
            ResultSet rs = exist.getResultSet();
            return rs.isBeforeFirst(); // returns false if the result set contains zero rows

        } catch (SQLException UnableToCheck) {
            throw new UnableToConnectException("Unable to check if value exists. Check network connection and try again");
        }
    }

    /**
     * gets ID of Metal
     * @return - MetalID
     */
    public long getID(){
        return ID;
    }

    /**
     * sets the ID of current Metal
     * @param ID - ID MetalID is being set to
     */
    public void setID(long ID){
        this.ID = ID;
    }

    /**
     * gets the name of current Metal
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * sets the name of current Metal
     * @param name - Name Metal is being set to
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * gets the atomic number of current Metal
     * @return - Atomic Number of current Metal
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * sets the AtomicNumber of current Metal
     * @param - New Atomic Number for Metal
     */
    public void setAtomicNumber(long atomicNumber){
        this.atomicMass = atomicNumber;
    }

    /**
     * gets the atomic mass of the Metal
     * @return - Atomic Mass of current Metal
     */
    public double getAtomicMass(){
        return atomicMass;
    }

    /**
     * sets the Atomic Mass of Metal
     * @param atomicMass - New Atomic Mass
     */
    public void setAtomicMass(double atomicMass){
        this.atomicMass = atomicMass;
    }

    /**
     * gets the ID of acid this Metal is dissolved by
     * @return - ID of current dissolvedBy value;
     */
    public long getDissolvedBy(){
        return dissolvedBy;
    }

    /**
     * sets the Id of acid that dissolves this Metal
     * @param dissolvedBy - ID of acid that dissolves this Metal
     */
    public void setDissolvedBy(long dissolvedBy){
        this.dissolvedBy = dissolvedBy;
    }
}
