package datasource;

import DTO.ChemicalDTO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChemicalTableDataGateway {
    //Find every element that makes up this compound
    //Compound: CO2: Key for CO2 -> madeOfTable(CompoundID); madeOfTable(ElementID) -> elements in Data table

    //Method for getting a list of ID's of elements that make up a compound
    //get all rows from table

    //Take in a list of IDs of elements (gotten from madeof); select all from table where the id matches; return a list of elements
//    ResultSet rs;


    /**
     * Selects a group of chemicals based on ids
     *
     * @param ID - list of ids
     */
    public static List<ChemicalDTO> getChemicals(List<Long> ID) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<ChemicalDTO> chemicalList = new ArrayList<ChemicalDTO>();
        String query = "SELECT * FROM chemical WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
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

