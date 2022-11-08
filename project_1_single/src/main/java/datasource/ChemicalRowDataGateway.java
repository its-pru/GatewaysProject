package datasource;

import exceptions.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


//use count for ID's?
public class ChemicalRowDataGateway {
    long id;
    String Name;
    long atomicNumber;
    double atomicMass;
    long Solute;
    long dissolvedBy;
    Type type;

    private static final String updateCreateString = "INSERT INTO Chemical" + " set name = ?, atomicNumber = ?, atomicMass = ?, solute = ?, dissolvedBy = ?, type = ?";
    private static final String updateFinderString = "SELECT * FROM Chemical WHERE name = ?";
    private static final String entryUpdateString = "UPDATE Chemical SET name = ?, atomicNumber = ?, atomicMass = ?, solute = ?, dissolvedBy = ?, type = ? WHERE ID = ?";
    private static final String existsString = "SELECT * FROM Chemical WHERE name = ? AND atomicNumber = ? AND atomicMass = ?";
    public static final String nameFromID = "SELECT * FROM Chemical WHERE id = ?";

    JDBC jdbc = JDBC.getJDBC();


    /**
     * Create Constructor for chemical row data gateway
     *
     * @param name         - name of the new chemical
     * @param atomicNumber -atomic number of the chemical
     * @param atomicMass   - atomic mass of the chemical
     * @param Solute       - id of solutes
     * @param dissolvedBy  - id of acid this chemical can be dissolved by
     * @throws Exception - exception when unable to connect to DB or when user trues to create duplicates
     */
    public ChemicalRowDataGateway(String name, long atomicNumber, double atomicMass, long Solute, long dissolvedBy, Type type) throws Exception {
        this.Name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.Solute = Solute;
        this.dissolvedBy = dissolvedBy;
        this.type = type;

        if (exists(name, atomicNumber, atomicMass, Solute, dissolvedBy)) {
            throw new AlreadyExistsException("Sorry. This already exists. Sounds like a skill issue to me");
        } else {
            try {
                PreparedStatement insertStatement = null;
                insertStatement = jdbc.getConnect().prepareStatement(updateCreateString, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, name);
                insertStatement.setLong(2, atomicNumber);
                insertStatement.setDouble(3, atomicMass);
                insertStatement.setLong(4, dissolvedBy);
                insertStatement.setLong(5, Solute);
                insertStatement.setString(6, type.toString());
                insertStatement.execute();

                ResultSet rs = insertStatement.getGeneratedKeys();
                rs.first();
                this.id = rs.getLong(1);

            } catch (SQLException UnableToCreateException) {
                UnableToCreateException.printStackTrace();
                throw new UnableToCreateException("This entry could not be added.");
            }
        }
    }

    /**
     * Finder Constructor for chemical row data gateway
     *
     * @param name - name of the chemical we are searching for
     * @throws Exception - exception when unable to connect to DB or when user trues to create duplicates
     */
    public ChemicalRowDataGateway(String name) throws EntryNotFoundException {
        try {
            PreparedStatement findStatement = null;
            findStatement = jdbc.getConnect().prepareStatement(updateFinderString, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            findStatement.setString(1, name);
            findStatement.execute();
            ResultSet results = findStatement.getResultSet();

            results.first();

            this.id = results.getLong("ID");
            this.Name = results.getString("name");
            this.atomicNumber = results.getLong("atomicNumber");
            this.atomicMass = results.getDouble("atomicMass");
            this.Solute = results.getLong("solute");
            this.dissolvedBy = results.getLong("dissolvedBy");
            this.type = Type.valueOf(results.getString("type"));
        } catch (SQLException e) {
            throw new EntryNotFoundException("Could not find an entry for this ID");
        }
    }

    public static ChemicalRowDataGateway createChemicalRowDataGateway(String name) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateCreateString);
            stmt.setString(1, name);
            stmt.setLong(2,0);
            stmt.setLong(3,0);
            stmt.setLong(4,0);
            stmt.setLong(5,0);
            stmt.setInt(6, 1);
            stmt.execute();
            return new ChemicalRowDataGateway(name);
        } catch (SQLException UnableToConnect) {
            throw new UnableToConnectException("Unable to create Chemical. Check connection and try again!");
        }
    }


    /**
     * Updates the database with the changes made to the gateway object
     *
     * @throws Exception - throws when object is not able to connect to the database
     */
    public void persist() throws Exception {
        PreparedStatement updateStatement = null;
        try {
            updateStatement = jdbc.getConnect().prepareStatement(entryUpdateString);
            updateStatement.setString(1, Name);
            updateStatement.setLong(2, atomicNumber);
            updateStatement.setDouble(3, atomicMass);
            updateStatement.setLong(4, Solute);
            updateStatement.setLong(5, dissolvedBy);
            updateStatement.setString(6, type.toString());
            updateStatement.setLong(7, id);

            updateStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnableToUpdateException("Unable to update entry!");
        }
    }


    /**
     * Deletes a chemical from the Chemical table
     *
     * @return - whether delete was successful or not
     * @throws Exception - throws when there isn't an entry found for the input ID
     */
    public boolean delete() throws EntryNotFoundException {
        //DELETE FROM Chemical where id =?
        Statement deleteStatement = null;
        try {
            deleteStatement = jdbc.getConnect().createStatement();
            String deleteString = "DELETE FROM Chemical WHERE id = " + Long.toString(id);
            deleteStatement.execute(deleteString);

        } catch (SQLException entryNotFound) {
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    /**
     * Checks to see if a chemical exists
     *
     * @param name         - name of the chemical we're looking for
     * @param atomicNumber - chemicals atomic number
     * @param atomicMass   - chemicals atomic mass
     * @param Solute       - solute for which acid
     * @param dissolvedBy  - which acid dissolves this
     * @return - boolean whether result is found or not
     * @throws Exception - throws when cannot connect to the database
     */
    public boolean exists(String name, long atomicNumber, double atomicMass, long Solute, long dissolvedBy) throws Exception {
        //select * from table where condition=value
        // SELECT EXISTS(SELECT * FROM table1 WHERE ...)
        PreparedStatement exists = null;
        try {
            exists = jdbc.getConnect().prepareStatement(existsString);
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

    public static String getNameFromID(long id) throws SQLException {
        PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(nameFromID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt.setLong(1, id);
        stmt.execute();
        ResultSet results = stmt.getResultSet();
        results.first();
        return results.getString("name");
    }

    /**
     * gets the name of the current chemical
     *
     * @return - returns the name of the chemical
     */

    public String getName() {
        return Name;
    }

    /**
     * sets the name of the current chemical
     *
     * @param name - name for chemical
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * gets the atomic number of the chemical
     *
     * @return - atomic number of chemical
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * sets the atomic number
     *
     * @param atomicNumber - atomic number being set
     */
    public void setAtomicNumber(long atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    /**
     * gets the id of the current element
     *
     * @return - id
     */
    public long getId() {
        return id;
    }

    /**
     * sets the id of the current element
     *
     * @param id - id being set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * gets the atomic mass of the chemical
     *
     * @return - atomic mass of the chemical
     */
    public double getAtomicMass() {
        return atomicMass;
    }

    /**
     * sets the atomic mass of this chemical
     *
     * @param atomicMass
     */
    public void setAtomicMass(double atomicMass) {
        this.atomicMass = atomicMass;
    }

    /**
     * gets the solute for this chemical
     *
     * @return - solute id
     */
    public long getSolute() {
        return Solute;
    }

    /**
     * sets the solute of this chemical
     *
     * @param solute - solute id
     */
    public void setSolute(long solute) {
        Solute = solute;
    }

    /**
     * gets the id of the chemical this is dissolved by
     *
     * @return - dissolvedBy id
     */
    public long getDissolvedBy() {
        return dissolvedBy;
    }

    /**
     * sets the id of the chemical this chemical is dissolved by
     *
     * @param dissolvedBy - dissolvedBy id
     */
    public void setDissolvedBy(long dissolvedBy) {
        this.dissolvedBy = dissolvedBy;
    }
}
