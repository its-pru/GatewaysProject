package datasource;

import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementRowDataGateway {
    long id;
    long atomicNumber;
    double atomicMass;

    public static final String existsQuery = "SELECT * FROM element WHERE atomicNumber = ? AND atomicMass = ?";
    public static final String createQuery = "INSERT INTO element (id, atomicNumber, atomicMass) VALUES (?,?,?)";
    public static final String findQuery = "SELECT * FROM element WHERE id = ?";
    public static final String updateQuery = "UPDATE element SET atomicNumber = ?, atomicMass = ? WHERE id = ?";
    public static final String deleteQuery = "DELETE FROM element WHERE id = ?";

    /**
     * Create constructor
     *
     * @param atomicNumber - atomic number of the element
     * @param atomicMass   - atomic mass of the element
     */
    public ElementRowDataGateway(long id, long atomicNumber, double atomicMass) throws Exception {
        if (exists(atomicNumber, atomicMass)) {
            throw new AlreadyExistsException("An element with this name already exists. Check name and try again!");
        }
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, atomicNumber);
            stmt.setDouble(3, atomicMass);
            stmt.execute();
            this.id = id;
            this.atomicNumber = atomicNumber;
            this.atomicMass = atomicMass;

        } catch (SQLException UnableToConnect) {
            UnableToConnect.printStackTrace();
            throw new UnableToConnectException("Unable to create element. Check connection and try again!");
        }
    }

    /**
     * Finder constructor
     *
     * @param id - ID of an already created element
     * @throws Exception - unable to find the row
     */
    public ElementRowDataGateway(long id) throws Exception {
        try {
            PreparedStatement findStatement = null;
            findStatement = JDBC.getJDBC().getConnect().prepareStatement(findQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            findStatement.setLong(1, id);
            findStatement.execute();
            ResultSet results = findStatement.getResultSet();
            results.first();
            this.id = results.getLong("ID");
            this.atomicNumber = results.getLong("atomicNumber");
            this.atomicMass = results.getDouble("atomicMass");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntryNotFoundException("Could not find an entry for this ID");
        }
    }

    /**
     * Updates the row in the database
     *
     * @throws Exception - Unable to update the row in the database
     */
    public void persist() throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setLong(1, atomicNumber);
            stmt.setDouble(2, atomicMass);
            stmt.setLong(3, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new UnableToUpdateException("Unable to update entry!");
        }

    }

    /**
     * Deletes the row in the database
     *
     * @return - true if deletion succeeded
     * @throws Exception - unable to find the row
     */
    public boolean delete() throws EntryNotFoundException {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException entryNotFound) {
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    /**
     * Checks if a base with identical parameters already exists
     *
     * @param atomicNumber - atomic number of the element
     * @param atomicMass   - atomic mass of the element
     * @return - true if it already exists
     * @throws Exception - unable to check the database at all
     */
    private boolean exists(long atomicNumber, double atomicMass) throws UnableToConnectException {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setLong(1, atomicNumber);
            stmt.setDouble(2, atomicMass);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException unableToConnect) {
            throw new UnableToConnectException("Unable to check if element already exists.");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(long atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public void setAtomicMass(double atomicMass) {
        this.atomicMass = atomicMass;
    }
}
