package datasource;

import exceptions.AlreadyExistsException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;
import exceptions.EntryNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcidRowDataGateway {
    long id;
    long solute;

    public static final String existsQuery = "SELECT * FROM acid WHERE id = ? AND solute = ?";
    public static final String createQuery = "INSERT INTO acid (id, solute) VALUES (?,?)";
    public static final String findQuery = "SELECT * FROM acid WHERE id = ?";
    public static final String updateQuery = "UPDATE acid SET solute = ? WHERE id = ?";
    public static final String deleteQuery = "DELETE FROM acid WHERE id = ?";

    /**
     * Create constructor for chemical
     *
     * @param id     - ID of an already created acid
     * @param solute - ID of object that dissolves in this acid
     */
    public AcidRowDataGateway(long id, long solute) throws Exception {
        if (exists(id, solute)) {
            throw new AlreadyExistsException("An identical Acid already exists");
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
     * Finder constructor for a chemical
     *
     * @param id - ID of an already created acid
     */
    public AcidRowDataGateway(long id) throws Exception {
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

    public boolean delete(long id) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteQuery);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException entryNotFound) {
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    private boolean exists(long id, long solute) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, solute);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException unableToConnect) {
            throw new UnableToConnectException("Unable to check if Acid already exists.");
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
