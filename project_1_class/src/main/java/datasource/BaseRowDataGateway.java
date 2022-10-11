package datasource;

import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseRowDataGateway {
    long id;
    long solute;
    public static final String existsQuery = "SELECT * FROM base WHERE id = ? AND solute = ?";
    public static final String createQuery = "INSERT INTO base (id, solute) VALUES (?,?)";
    public static final String findQuery = "SELECT * FROM base WHERE id = ?";
    public static final String updateQuery = "UPDATE base SET solute = ? WHERE id = ?";
    public static final String deleteQuery = "DELETE FROM base WHERE id = ?";

    /**
     * Create constructor
     *
     * @param id     - ID of an already created base
     * @param solute - ID of object that dissolves in this base
     */
    public BaseRowDataGateway(long id, long solute) throws Exception {
        if (exists(id, solute)) {
            throw new AlreadyExistsException("An identical base already exists");
        }
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, solute);
            stmt.execute();
            this.id = id;
            this.solute = solute;

        } catch (SQLException UnableToConnect) {
            throw new UnableToConnectException("Unable to create Chemical. Check connection and try again!");
        }
    }

    /**
     * Finder constructor
     *
     * @param id - ID of an already created base
     * @throws Exception - unable to find the row
     */
    public BaseRowDataGateway(long id) throws Exception {
        try {
            PreparedStatement findStatement = null;
            findStatement = JDBC.getJDBC().getConnect().prepareStatement(findQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            findStatement.setLong(1, id);
            findStatement.execute();
            ResultSet results = findStatement.getResultSet();
            results.first();
            this.id = id;
            this.solute = results.getLong("solute");
        } catch (SQLException e) {
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
            stmt.setLong(1, solute);
            stmt.setLong(2, id);
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
    public boolean delete() throws Exception {
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
     * @param id     - ID of a base
     * @param solute - ID of a chemical that dissolves in this base
     * @return - true if it already exists
     * @throws Exception - unable to check the database at all
     */
    private boolean exists(long id, long solute) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, solute);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException unableToConnect) {
            throw new UnableToConnectException("Unable to check if base already exists.");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSolute() {
        return solute;
    }

    public void setSolute(long solute) {
        this.solute = solute;
    }
}
