package datasource;

import DTO.AcidDTO;
import DTO.ChemicalDTO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AcidTableDataGateway {
    /**
     * Selects a group of acids based on ids
     *
     * @param ID - list of ids
     */
    public static List<AcidDTO> getAcids(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<AcidDTO> acidList = new ArrayList<AcidDTO>();
        String query = "SELECT * FROM acid WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for (int i = 0; i < ID.size(); i++) {
            stmt.setLong(i + 1, ID.get(i));
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            long id = rs.getLong("ID");
            long solute = rs.getLong("solute");
            acidList.add(new AcidDTO(id, solute));
        }

        return acidList;
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
