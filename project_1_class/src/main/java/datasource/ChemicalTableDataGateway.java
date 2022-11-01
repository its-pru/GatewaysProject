package datasource;

import DTO.ChemicalDTO;
import exceptions.UnableToConnectException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChemicalTableDataGateway {
    public static List<ChemicalDTO> getAllChemicals() throws UnableToConnectException {
        try {
            List<ChemicalDTO> chemicalList = new ArrayList<ChemicalDTO>();
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement("SELECT * FROM chemical");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("name");
                chemicalList.add(new ChemicalDTO(id, name));
            }
            return chemicalList;
        } catch (SQLException e) {
            throw new UnableToConnectException("Unable to get Chemicals");
        }
    }


    /**
     * Selects a group of chemicals based on ids
     *
     * @param ID - list of ids
     */
    public static List<ChemicalDTO> getChemicals(List<Long> ID) throws UnableToConnectException {
        List<ChemicalDTO> chemicalList = new ArrayList<ChemicalDTO>();
        String query = "SELECT * FROM chemical WHERE ID in (";
        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(queryBuilder(ID.size(), query));
            for (int i = 0; i < ID.size(); i++) {
                stmt.setLong(i + 1, ID.get(i));
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("name");
                chemicalList.add(new ChemicalDTO(id, name));
            }

            return chemicalList;
        } catch (SQLException e) {
            throw new UnableToConnectException("Unable to get Chemicals");
        }

    }


    /**
     * builds an sql statement based on number of ids
     *
     * @param numIDs    - number of ids
     * @param beginning - beginning of string before formatting
     * @return
     */
    public static String queryBuilder(int numIDs, String beginning) {

        StringBuilder sb = new StringBuilder(beginning);

        for (int i = 0; i < numIDs; i++) {
            sb.append(" ?");
            if (i != (numIDs - 1)) {
                sb.append(",");
            }
        }
        sb.append(")");

        return sb.toString();
    }

    // right now this just clears every row from every table in the database and resets the auto increment
    public static void rollback() throws UnableToConnectException {
        try {
            PreparedStatement delete = JDBC.getJDBC().getConnect().prepareStatement("DELETE FROM chemical");
            delete.execute();
            PreparedStatement reset = JDBC.getJDBC().getConnect().prepareStatement("ALTER TABLE chemical AUTO_INCREMENT = 1");
            reset.execute();
        } catch (SQLException e) {
            throw new UnableToConnectException("Unable to rollback database");
        }
    }

}

