package datasource;

import DTO.BaseDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BaseTableDataGateway {
    /**
     * Selects a group of bases based on ids
     *
     * @param ID - list of ids
     */
    public static List<BaseDTO> getBases(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<BaseDTO> acidList = new ArrayList<BaseDTO>();
        String query = "SELECT * FROM base WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for (int i = 0; i < ID.size(); i++) {
            stmt.setLong(i + 1, ID.get(i));
        }

        stmt.execute();
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            long id = rs.getLong("ID");
            long solute = rs.getLong("solute");
            acidList.add(new BaseDTO(id, solute));
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
