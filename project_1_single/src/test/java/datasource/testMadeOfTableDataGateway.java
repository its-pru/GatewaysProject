package datasource;

import Exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class testMadeOfTableDataGateway extends TestCase{

    @Test
    public void testCreate() throws Exception{
    }
    @Test
    public void testFindCompounds() throws Exception{
        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 1, 1);
        ChemicalRowDataGateway oxygen = new ChemicalRowDataGateway("Oxygen", 2, 2, 2,2);
        ChemicalRowDataGateway h2o = new ChemicalRowDataGateway("H2O", 3, 3, 3,3);

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getId());
        chemicalIDs.add(oxygen.getId());

        MadeOfTableDataGateway testGateway = new MadeOfTableDataGateway();
        testGateway.CreateNewCompound(h2o.getId(), chemicalIDs);

        boolean success = testGateway.findCompoundsWithOneElement(oxygen.getId());
        assertTrue(success);
        long compoundID = testGateway.getCompoundID();
        assertEquals(compoundID, h2o.getId());

        testGateway.deleteCompounds(h2o.getId());
        hydrogen.delete(hydrogen.getId());
        oxygen.delete(oxygen.getId());

        h2o.delete(h2o.getId());

    }

    @Test
    public void testDeleteElement() throws Exception{
        boolean thrown = false;
        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 2, 2);
        ChemicalRowDataGateway helium = new ChemicalRowDataGateway("Helium", 2, 2, 1, 1);
        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(helium.getId());

        MadeOfTableDataGateway test = new MadeOfTableDataGateway();
        test.CreateNewCompound(hydrogen.getId(), chemicalIDs);

        try {
        test.deleteElements(helium.getId());
        }
        catch (Exception e) {
            thrown = true;
        }
        hydrogen.delete(hydrogen.getId());
        helium.delete(helium.getId());
        assertFalse(thrown);

    }

    @Test
    public void testsDeleteCompound() throws Exception{
        boolean thrown = false;
        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 2, 2);
        ChemicalRowDataGateway helium = new ChemicalRowDataGateway("Helium", 2, 2, 1, 1);
        ChemicalRowDataGateway water = new ChemicalRowDataGateway("water",5,5,5,5);

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getId());
        chemicalIDs.add(helium.getId());


        MadeOfTableDataGateway tester = new MadeOfTableDataGateway();
        tester.CreateNewCompound(water.getId(), chemicalIDs);
        assertNotNull(water);
        try {
            tester.deleteCompounds(water.getId());
        }
        catch (Exception e) {
            fail();
        }
        water.delete(water.getId());
        hydrogen.delete(hydrogen.getId());
        helium.delete(helium.getId());


    }

}
