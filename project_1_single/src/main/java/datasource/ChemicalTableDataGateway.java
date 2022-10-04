package datasource;

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
    JDBC jdbc = JDBC.getJDBC();
    List<Long> ID = new ArrayList<Long>();
    ResultSet rs;

    /**
     * empty constructor to initialize gateway
     */
    public ChemicalTableDataGateway () {}

    /**
     * Selects a group of chemicals based on ids
     * @param ID - list of ids
     */
    public ChemicalTableDataGateway(List<Long> ID) throws Exception{
        this.ID = ID;
        String query = "SELECT * FROM Chemical WHERE ID in (";
        PreparedStatement stmt = jdbc.getConnect().prepareStatement(queryBuilder(ID.size(), query));
        for(int i = 0; i < ID.size(); i++){
            stmt.setLong(i+1, ID.get(i));
        }
        rs = stmt.executeQuery();
    }

    /**
     * returns the result set of Query
     * @return
     */
    public ResultSet getResultSet(){
        return rs;
    }

    /**
     * builds an sql statement based on number of ids
     * @param numIDs - number of ids
     * @param beginning - beginning of string before formatting
     * @return
     */
    public String queryBuilder(int numIDs, String beginning) {

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

    public boolean deleteElements(Long ID) {

        return true;
    }
}
