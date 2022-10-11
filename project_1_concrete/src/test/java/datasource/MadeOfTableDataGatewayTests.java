package datasource;

import DTO.MadeOfDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MadeOfTableDataGatewayTests extends TestCase {
    @Test
    public void testFindElements() throws Exception{
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1);
        ElementRowDataGateway oxygen = new ElementRowDataGateway(2,"Oxygen", 2, 2);
        CompoundRowDataGateway h2o = new CompoundRowDataGateway(3, "H2O");

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(oxygen.getID());

        MadeOfTableDataGateway.createNewCompound(h2o.getID(), chemicalIDs);

        List<Long> ElementIDs = MadeOfTableDataGateway.findElements(h2o.getID());

        assertTrue(ElementIDs.contains(hydrogen.getID()));
        assertTrue(ElementIDs.contains(oxygen.getID()));


        MadeOfTableDataGateway.deleteCompounds(h2o.getID());
        h2o.delete(h2o.getID());
        hydrogen.delete(hydrogen.getID());
        oxygen.delete(oxygen.getID());

    }

    @Test
    public void testFindCompoundsWithOneElement() throws Exception {
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(1,"Hydrogen", 1, 1);
        CompoundRowDataGateway hydrogenPeroxide = new CompoundRowDataGateway(2,"H202");
        ElementRowDataGateway oxygen = new ElementRowDataGateway(3,"Oxygen", 2, 2);
        CompoundRowDataGateway h2o = new CompoundRowDataGateway(4,"H2O");

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(oxygen.getID());

        MadeOfTableDataGateway.createNewCompound(h2o.getID(), chemicalIDs);
        MadeOfTableDataGateway.createNewCompound(hydrogenPeroxide.getID(), chemicalIDs);

        List<Long> list = MadeOfTableDataGateway.findCompounds(oxygen.getID());

        assertTrue(list.contains(hydrogenPeroxide.getID()));
        assertTrue(list.contains(h2o.getID()));


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
        test.createNewCompound(hydrogen.getID(), chemicalIDs);

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
        CompoundRowDataGateway water = new CompoundRowDataGateway(3,"water");

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getID());
        chemicalIDs.add(helium.getID());


        MadeOfTableDataGateway tester = new MadeOfTableDataGateway();
        tester.createNewCompound(water.getID(), chemicalIDs);
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
