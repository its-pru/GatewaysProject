package datasource;

import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import exceptions.UnableToUpdateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetalRowDataGateway {
    long id;

    long dissolvedBy;

    public static final String existsQuery = "SELECT * FROM metal WHERE id = ? AND dissolvedBy = ?";
    public static final String createQuery = "INSERT INTO metal (id, dissolvedBy) VALUES (?,?)";
    public static final String findQuery = "SELECT * FROM metal WHERE id = ?";
    public static final String updateQuery = "UPDATE metal SET dissolvedBy = ? WHERE id = ?";
    public static final String deleteQuery = "DELETE FROM metal WHERE id = ?";


    public MetalRowDataGateway(long id, long dissolvedBy) throws Exception {
        if (exists(id, dissolvedBy)) {
            throw new AlreadyExistsException("An identical metal already exists");
        }
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, dissolvedBy);
            stmt.execute();
            this.id = id;
            this.dissolvedBy = dissolvedBy;

        } catch (SQLException UnableToConnect) {
            throw new UnableToConnectException("Unable to create Chemical. Check connection and try again!");
        }
    }


    public MetalRowDataGateway(long id) throws Exception {
        try {
            PreparedStatement findStatement = null;
            findStatement = JDBC.getJDBC().getConnect().prepareStatement(findQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            findStatement.setLong(1, id);
            findStatement.execute();
            ResultSet results = findStatement.getResultSet();
            results.first();
            this.id = id;
            this.dissolvedBy = results.getLong("dissolvedBy");
        } catch (SQLException e) {
            throw new EntryNotFoundException("Could not find an entry for this ID");
        }

    }

    public void persist() throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(updateQuery);
            stmt.setLong(1, dissolvedBy);
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

    private boolean exists(long id, long dissolvedBy) throws Exception {
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(existsQuery);
            stmt.setLong(1, id);
            stmt.setLong(2, dissolvedBy);
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

    public long getDissolvedBy() {
        return dissolvedBy;
    }

    public void setDissolvedBy(long dissolvedBy) {
        this.dissolvedBy = dissolvedBy;
    }
}
