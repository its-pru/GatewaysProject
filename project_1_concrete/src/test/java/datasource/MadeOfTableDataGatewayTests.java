package datasource;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MadeOfTableDataGatewayTests extends TestCase {
    @Test
    public void testFindElements() throws Exception{
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1);
        ElementRowDataGateway oxygen = new ElementRowDataGateway(2,"Oxygen", 2, 2);
        ElementRowDataGateway h2o = new ElementRowDataGateway(3,"H2O", 3, 3);

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(oxygen.getID());

        MadeOfTableDataGateway.CreateNewCompound(h2o.getID(), chemicalIDs);

        assertEquals(MadeOfTableDataGateway.findElements(h2o.getID()).getChemicalID(), h2o.getID());
        assertEquals(MadeOfTableDataGateway.findElements(h2o.getID()).getListOfElementIDs(), chemicalIDs);

        MadeOfTableDataGateway.deleteCompounds(h2o.getID());
        h2o.delete(h2o.getID());
        hydrogen.delete(hydrogen.getID());
        oxygen.delete(oxygen.getID());

    }

    @Test
    public void testFindCompoundsWithOneElement() throws Exception {
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1);
        ElementRowDataGateway hydrogenPeroxide = new ElementRowDataGateway(2,"H202", 4, 4);
        ElementRowDataGateway oxygen = new ElementRowDataGateway(3,"Oxygen", 2, 2);
        ElementRowDataGateway h2o = new ElementRowDataGateway(4,"H2O", 3, 3);

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(oxygen.getID());

        MadeOfTableDataGateway.CreateNewCompound(h2o.getID(), chemicalIDs);
        MadeOfTableDataGateway.CreateNewCompound(hydrogenPeroxide.getID(), chemicalIDs);

        List<MadeOfDTO> list = MadeOfTableDataGateway.findCompoundsWithOneElement(oxygen.getID());

        assertEquals (list.get(0).getChemicalID(), h2o.getID());
        assertEquals (list.get(1).getChemicalID(), hydrogenPeroxide.getID());

        MadeOfTableDataGateway.deleteCompounds(h2o.getID());
        MadeOfTableDataGateway.deleteCompounds(hydrogenPeroxide.getID());
        h2o.delete(h2o.getID());
        hydrogen.delete(hydrogen.getID());
        oxygen.delete(oxygen.getID());
        hydrogenPeroxide.delete((hydrogenPeroxide.getID()));
    }

    @Test
    public void testDeleteElement() throws Exception{
        boolean thrown = false;
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1, 2, 2);
        ElementRowDataGateway helium = new ElementRowDataGateway(2,"Helium", 2, 2, 1, 1);
        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(helium.getID());

        MadeOfTableDataGateway test = new MadeOfTableDataGateway();
        test.CreateNewCompound(hydrogen.getID(), chemicalIDs);

        try {
            test.deleteElements(helium.getID());
        }
        catch (Exception e) {
            thrown = true;
        }
        hydrogen.delete(hydrogen.getID());
        helium.delete(helium.getID());
        assertFalse(thrown);

    }

    @Test
    public void testsDeleteCompound() throws Exception{
        boolean thrown = false;
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1);
        ElementRowDataGateway helium = new ElementRowDataGateway(2,"Helium", 2, 2);
        ElementRowDataGateway water = new ElementRowDataGateway(3,"water",5,5);

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(helium.getID());


        MadeOfTableDataGateway tester = new MadeOfTableDataGateway();
        tester.CreateNewCompound(water.getID(), chemicalIDs);
        assertNotNull(water);
        try {
            tester.deleteCompounds(water.getID());
        }
        catch (Exception e) {
            fail();
        }
        water.delete(water.getID());
        hydrogen.delete(hydrogen.getID());
        helium.delete(helium.getID());


    }
}
