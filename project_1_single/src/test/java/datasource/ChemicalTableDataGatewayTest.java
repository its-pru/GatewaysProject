package datasource;

import Exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ChemicalTableDataGatewayTest extends TestCase {


    @Test
    public void testQueryBuilder() throws Exception{

        ChemicalTableDataGateway testQueryBuilder = new ChemicalTableDataGateway();
        String query = "SELECT * FROM Chemical WHERE ID in (";
        String test = testQueryBuilder.queryBuilder(3, query);

        assertEquals("SELECT * FROM Chemical WHERE ID in ( ?, ?, ?)", test);


    }

    @Test
    public void testTableDataGatewayCreate() throws Exception {
        ChemicalRowDataGateway testChem1 = new ChemicalRowDataGateway("TestChem1", 100, 100, 100, 100);
        ChemicalRowDataGateway testChem2 = new ChemicalRowDataGateway("TestChem2", 200, 200, 200, 200);

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(testChem1.getId());
        listOfIDs.add(testChem2.getId());

        ChemicalTableDataGateway testQueryBuilder = new ChemicalTableDataGateway(listOfIDs);
        ResultSet rs = testQueryBuilder.getResultSet();

        while (rs.next()) {
            long listID = listOfIDs.get(0);
            long rsID = rs.getLong("ID");

            assertEquals(listID, rsID);
            listOfIDs.remove(0);
        }
        testChem1.delete(testChem1.getId());
        testChem2.delete(testChem2.getId());
    }
}
