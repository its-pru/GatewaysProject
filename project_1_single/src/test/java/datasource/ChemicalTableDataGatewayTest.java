package datasource;


import DTO.ChemicalDTO;
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
        String query = "SELECT * FROM Chemical WHERE ID in (";
        String test = ChemicalTableDataGateway.queryBuilder(3, query);

        assertEquals("SELECT * FROM Chemical WHERE ID in ( ?, ?, ?)", test);


    }

    @Test
    public void testGetChemicals() throws Exception {
        ChemicalRowDataGateway testChem1 = new ChemicalRowDataGateway("TestChem1", 100, 100, 100, 100);
        ChemicalRowDataGateway testChem2 = new ChemicalRowDataGateway("TestChem2", 200, 200, 200, 200);

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(testChem1.getId());
        listOfIDs.add(testChem2.getId());
        List<ChemicalDTO> chemicalList = ChemicalTableDataGateway.getChemicals(listOfIDs);

        assertEquals(testChem1.getName(), chemicalList.get(0).getName());
        assertEquals(testChem2.getName(), chemicalList.get(1).getName());

        testChem1.delete(testChem1.getId());
        testChem2.delete(testChem2.getId());
    }
}
