package datasource;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import DTO.MadeOfDTO;

import javax.swing.*;

public class MadeOfGatewayTest extends TestCase{

    @Test
    public void testFindElements() throws Exception{
        ChemicalRowDataGateway hydrogen1 = new ChemicalRowDataGateway("Hydrogen");
        ChemicalRowDataGateway oxygen1 = new ChemicalRowDataGateway("Oxygen");

        ElementRowDataGateway hydrogen = new ElementRowDataGateway(hydrogen1.getId(), 1, 1.00784);
        ElementRowDataGateway oxygen = new ElementRowDataGateway(oxygen1.getId(), 8, 15.999);
        ChemicalRowDataGateway h2o = new ChemicalRowDataGateway("H2O");

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen1.getId());
        chemicalIDs.add(oxygen1.getId());

        MadeOfTableDataGateway.CreateNewCompound(h2o.getId(), chemicalIDs);

        assertEquals(MadeOfTableDataGateway.findElements(h2o.getId()).getChemicalID(), h2o.getId());
        assertEquals(MadeOfTableDataGateway.findElements(h2o.getId()).getListOfElementIDs(), chemicalIDs);

        //MadeOfTableDataGateway.deleteCompounds(h2o.getId());
        h2o.delete();
        hydrogen1.delete();
        oxygen1.delete();

    }

    @Test
    public void testFindCompoundsWithOneElement() throws Exception {
        ChemicalRowDataGateway hydrogen1 = new ChemicalRowDataGateway("Hydrogen");
        ChemicalRowDataGateway oxygen1 = new ChemicalRowDataGateway("oxygen");

        ElementRowDataGateway hydrogen = new ElementRowDataGateway(hydrogen1.getId(), 1, 1);
        ChemicalRowDataGateway hydrogenPeroxide = new ChemicalRowDataGateway("H202");
        ElementRowDataGateway oxygen = new ElementRowDataGateway(oxygen1.getId(), 8, 15);
        ChemicalRowDataGateway h2o = new ChemicalRowDataGateway("H2O");

        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(hydrogen.getId());
        chemicalIDs.add(oxygen.getId());

        MadeOfTableDataGateway.CreateNewCompound(h2o.getId(), chemicalIDs);
        MadeOfTableDataGateway.CreateNewCompound(hydrogenPeroxide.getId(), chemicalIDs);

        List<MadeOfDTO> list = MadeOfTableDataGateway.findCompoundsWithOneElement(oxygen.getId());

        assertEquals (list.get(0).getChemicalID(), h2o.getId());
        assertEquals (list.get(1).getChemicalID(), hydrogenPeroxide.getId());

        MadeOfTableDataGateway.deleteCompounds(h2o.getId());
        MadeOfTableDataGateway.deleteCompounds(hydrogenPeroxide.getId());
        hydrogen1.delete();
        oxygen1.delete();
        h2o.delete();
        hydrogenPeroxide.delete();
    }

    @Test
    public void testDeleteElement() throws Exception{
        boolean thrown = false;

        ChemicalRowDataGateway helium1 = new ChemicalRowDataGateway("helium1");
        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("hydrogen1");
        ElementRowDataGateway helium = new ElementRowDataGateway(helium1.getId(), 2, 2);
        List<Long> chemicalIDs = new ArrayList<>();
        chemicalIDs.add(helium.getId());

        MadeOfTableDataGateway.CreateNewCompound(hydrogen.getId(), chemicalIDs);

        try {
            MadeOfTableDataGateway.deleteElements(helium.getId());
        }
        catch (Exception e) {
            thrown = true;
        }
        MadeOfTableDataGateway.deleteCompounds(hydrogen.getId());
        helium1.delete();
        hydrogen.delete();
        helium.delete();
        assertFalse(thrown);

    }

    @Test
    public void testsDeleteCompound() throws Exception{
        boolean thrown = false;

        ChemicalRowDataGateway hydrogen1 = new ChemicalRowDataGateway("hydrogen");
        ChemicalRowDataGateway helium1 = new ChemicalRowDataGateway("helium");
        ElementRowDataGateway hydrogen = new ElementRowDataGateway(hydrogen1.getId(), 1, 1);
        ElementRowDataGateway helium = new ElementRowDataGateway(helium1.getId(), 2, 2);
        ChemicalRowDataGateway water = new ChemicalRowDataGateway("water");

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
        MadeOfTableDataGateway.deleteElements(hydrogen.getId());
        MadeOfTableDataGateway.deleteElements(helium.getId());
        helium1.delete();
        hydrogen1.delete();
        water.delete();
        hydrogen.delete();
        helium.delete();


    }

}

