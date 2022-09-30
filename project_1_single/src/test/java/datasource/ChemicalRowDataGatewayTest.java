package datasource;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ChemicalRowDataGatewayTest extends TestCase {
    @Test
    public void testCreate(){
        ChemicalRowDataGateway testGateway = new ChemicalRowDataGateway();
        assertNotNull(testGateway);
    }
    @Test
    public void testJDBCConnect(){
        ChemicalRowDataGateway testGateway = new ChemicalRowDataGateway();
    }

    @Test
    public void testInsertNewChemical(){
        ChemicalRowDataGateway testInsert = new ChemicalRowDataGateway("TestChemical", "Earth", 100, 1.2, new ArrayList<Long>(), 19, 44, new ArrayList<Long>());
    }

    public void testFindChemical(){
        ChemicalRowDataGateway testFind = new ChemicalRowDataGateway();
        boolean success = testFind.find("TestChemical");
        assertEquals(true, success);
    }

    @Test
    public void testDeleteChemical(){
        ChemicalRowDataGateway testDelete = new ChemicalRowDataGateway("TestChemical", "Earth", 100, 1.2, new ArrayList<Long>(), 19, 44, new ArrayList<Long>());
        boolean success = testDelete.find("TestChemical");
        assertEquals(false, success);
    }
}