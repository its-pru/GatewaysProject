package datasource;

import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToCreateException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementRowDataGateway{

    //Instance variables for each element
    long ID;
    String name;
    long atomicNumber;
    double atomicMass;

    private static final String elementCreateString = "INSERT INTO element VALUES (?,?,?,?)";
    private static final String existsString= "SELECT * FROM element WHERE name = ? OR atomicNumber = ? OR atomicMass = ?";
    private static final String finderString = " SELECT * FROM element WHERE name = ?";
    private static final String updateString = "UPDATE element SET name = ?, atomicNumber = ?, atomicMass = ? WHERE ID = ?";
    private static final String deleteString = "DELETE FROM element WHERE name = ?";

    /**
     * Create constructor for a new Element
     * @param name - Name of the new Element
     * @param atomicNumber - Atomic number of new element
     * @param atomicMass - Atomic mass of new element
     * @throws Exception - throws when class is unable to connect ot database
     */
    public ElementRowDataGateway(String name, long atomicNumber, double atomicMass) throws Exception{
        this.ID = KeyHandler.getNewKey();
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;

        if(exists(name, atomicNumber, atomicMass)){
            throw new UnableToCreateException("There is already an element for these parameters. Change them and try again");
        }else {
            try {
                PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(elementCreateString);
                stmt.setLong(1, ID);
                stmt.setString(2, name);
                stmt.setLong(3, atomicNumber);
                stmt.setDouble(4, atomicMass);
                stmt.execute();
            } catch (SQLException unableToCreate) {
                unableToCreate.printStackTrace();
                throw new UnableToCreateException("Unable to create Element. Check inputs and try again");
            }
        }
    }
    public ElementRowDataGateway(long id, String name, long atomicNumber, double atomicMass) throws Exception{
        this.ID = id;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;

        if(exists(name, atomicNumber, atomicMass)){
            throw new UnableToCreateException("There is already an element for these parameters. Change them and try again");
        }else {
            try {
                PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(elementCreateString);
                stmt.setLong(1, ID);
                stmt.setString(2, name);
                stmt.setLong(3, atomicNumber);
                stmt.setDouble(4, atomicMass);
                stmt.execute();
            } catch (SQLException unableToCreate) {
                unableToCreate.printStackTrace();
                throw new UnableToCreateException("Unable to create Element. Check inputs and try again");
            }
        }
    }

    /**
     * Finder constructor for Element
     * @param name - Name of Element being searched for
     * @throws Exception
     */
    public ElementRowDataGateway(String name) throws Exception{
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(finderString);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            this.ID = ID;
            this.name = rs.getString("name");
            this.atomicNumber = rs.getLong("atomicNumber");
            this.atomicMass = rs.getDouble("atomicMass");


        }catch (SQLException notFound){
            throw new EntryNotFoundException("Element for this ID not found. Check ID and try again");
        }
    }

    /**
     * Updates row in database with current values held
     * @throws Exception - throws when persist fails to update the element
     */
    public void persist() throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateString);
            stmt.setString(1, name);
            stmt.setLong(2, atomicNumber);
            stmt.setDouble(3, atomicMass);
            stmt.setLong(4, ID);
            stmt.execute();
        }catch (SQLException e){
            throw new UnableToUpdateException("Unable to update element. Check network connection and try again!");
        }
    }

    /**
     * deletes an Element from the Database
     *
     * @return true - returns true if the id is deleted
     * @throws Exception - throws when Element being deleted isn't found
     */
    public static boolean delete(String name)throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteString);
            stmt.setString(1, name);
            stmt.execute();
        }catch (SQLException e){
            throw new EntryNotFoundException("Could not find Element with this ID. Check ID and try again");
        }
        return true;
    }

    /**
     * Checks to see if an element exists for a set of parameters
     * @param name - name being searched for
     * @param atomicNumber - atomic number being searched for
     * @param atomicMass - atomic mass being searched for
     * @return - True if exists, False if not
     * @throws UnableToConnectException
     */
    public boolean exists(String name, long atomicNumber, double atomicMass) throws UnableToConnectException {
        PreparedStatement exists = null;
        try{
            exists = JDBC.getJDBC().getConnect().prepareStatement(existsString);
            exists.setString(1, name);
            exists.setLong(2, atomicNumber);
            exists.setDouble(3, atomicMass);
            exists.execute();
            ResultSet rs = exists.getResultSet();
            return rs.isBeforeFirst(); // returns false if the result set contains zero rows

        } catch (SQLException throwables) {
            throw new UnableToConnectException("Unable to check if value exists. Check network connection and try again");
        }
    }

    /**
     * gets ID of element
     * @return - ElementID
     */
    public long getID(){
        return ID;
    }

    /**
     * sets the ID of current Element
     * @param ID - ID elementID is being set to
     */
    public void setID(long ID){
        this.ID = ID;
    }

    /**
     * gets the name of current Element
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * sets the name of current Element
     * @param name - Name Element is being set to
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * gets the atomic number of current element
     * @return - Atomic Number of current Element
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * sets the AtomicNumber of current Element
     * @param - New Atomic Number for Element
     */
    public void setAtomicNumber(long atomicNumber){
        this.atomicMass = atomicNumber;
    }

    /**
     * gets the atomic mass of the element
     * @return - Atomic Mass of current Element
     */
    public double getAtomicMass(){
        return atomicMass;
    }

    /**
     * sets the Atomic Mass of Element
     * @param atomicMass - New Atomic Mass
     */
    public void setAtomicMass(double atomicMass){
        this.atomicMass = atomicMass;
    }
}
