package datasource;

import DTO.ElementDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ElementTableDataGateway {
    /**
     * Selects a group of chemicals based on ids
     *
     * @param ID - list of ids
     */
    public static List<ElementDTO> getElements(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<ElementDTO> ElementList = new ArrayList<ElementDTO>();
        String query = "SELECT * FROM Element WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for (int i = 0; i < ID.size(); i++) {
            stmt.setLong(i + 1, ID.get(i));
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            long id = rs.getLong("ID");
            String name = rs.getString("name");
            long atomicNumber = rs.getLong("atomicNumber");
            double atomicMass = rs.getDouble("atomicMass");
            ElementList.add(new ElementDTO(id, name, atomicNumber, atomicMass));
        }

        return ElementList;
    }


    /**
     * builds a sql statement based on number of ids
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
