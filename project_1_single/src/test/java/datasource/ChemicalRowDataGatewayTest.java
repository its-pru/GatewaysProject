package datasource;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChemicalRowDataGatewayTest extends TestCase {
//    @Test
//    public void testCreate() throws SQLException {
//        ChemicalRowDataGateway testInsert = new ChemicalRowDataGateway("TestChemical2", 10, 10, 10,10);
//        assertNotNull(testInsert);
//    }

//    @Test
//    public void testInsertNewChemical() throws SQLException {
//        ChemicalRowDataGateway testInsert = new ChemicalRowDataGateway("TestChemical", "Earth", 100, 1.2, new ArrayList<Long>(), 19, 44, new ArrayList<Long>());
//
//    }

    public void testFindChemical(){
        ChemicalRowDataGateway testFind = new ChemicalRowDataGateway();
        boolean success = testFind.find("TestChemical");
        assertEquals(true, success);
    }

//    @Test
//    public void testDeleteChemical() throws SQLException {
//        ChemicalRowDataGateway testDelete = new ChemicalRowDataGateway();
//        boolean success = testDelete.delete(1);
//        assertEquals(true, success);
//    }
}