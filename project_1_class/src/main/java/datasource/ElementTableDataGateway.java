package datasource;

import DTO.BaseDTO;
import DTO.ChemicalDTO;
import DTO.ElementDTO;
import exceptions.UnableToConnectException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElementTableDataGateway {
    public static List<ElementDTO> getAllElements() throws UnableToConnectException {
        try {
            List<ElementDTO> elementList = new ArrayList<ElementDTO>();
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement("SELECT * FROM element");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                long id = rs.getLong("ID");
                long atomicNumber = rs.getLong("atomicNumber");
                double atomicMass = rs.getDouble("atomicMass");
                elementList.add(new ElementDTO(id, atomicNumber, atomicMass));
            }
            return elementList;
        } catch (SQLException e) {
            throw new UnableToConnectException("Unable to get Chemicals");
        }
    }
    /**
     * Selects a group of elements based on ids
     *
     * @param ID - list of ids
     */
    public static List<ElementDTO> getElements(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<ElementDTO> elementList = new ArrayList<ElementDTO>();
        String query = "SELECT * FROM element WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for (int i = 0; i < ID.size(); i++) {
            stmt.setLong(i + 1, ID.get(i));
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            long id = rs.getLong("id");
            long atomicNumber = rs.getLong("atomicNumber");
            double atomicMass = rs.getDouble("atomicMass");
            elementList.add(new ElementDTO(id, atomicNumber, atomicMass));
        }

        return elementList;
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
}
