//package datasource;
//
//import junit.framework.TestCase;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import DTO.MadeOfDTO;
//
//public class MadeOfTableDataGatewayTest extends TestCase {
//
//    @Test
//    public void testCreate() throws Exception {
//    }
//
//    @Test
//    public void testFindElements() throws Exception {
//        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 1, 1, Type.ELEMENT);
//        ChemicalRowDataGateway oxygen = new ChemicalRowDataGateway("Oxygen", 2, 2, 2, 2, Type.ELEMENT);
//        ChemicalRowDataGateway h2o = new ChemicalRowDataGateway("H2O", 3, 3, 3, 3, Type.COMPOUND);
//
//        List<Long> chemicalIDs = new ArrayList<>();
//        chemicalIDs.add(hydrogen.getId());
//        chemicalIDs.add(oxygen.getId());
//
//        MadeOfTableDataGateway.CreateNewCompound(h2o.getId(), chemicalIDs);
//
//        assertEquals(MadeOfTableDataGateway.findElements(h2o.getId()).getChemicalID(), h2o.getId());
//        assertEquals(MadeOfTableDataGateway.findElements(h2o.getId()).getListOfElementIDs(), chemicalIDs);
//
//        MadeOfTableDataGateway.deleteCompounds(h2o.getId());
//        h2o.delete();
//        hydrogen.delete();
//        oxygen.delete();
//
//    }
//
//    @Test
//    public void testFindCompoundsWithOneElement() throws Exception {
//        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 1, 1, Type.ELEMENT);
//        ChemicalRowDataGateway hydrogenPeroxide = new ChemicalRowDataGateway("H202", 4, 4, 2, 1, Type.COMPOUND);
//        ChemicalRowDataGateway oxygen = new ChemicalRowDataGateway("Oxygen", 2, 2, 2, 2, Type.ELEMENT);
//        ChemicalRowDataGateway h2o = new ChemicalRowDataGateway("H2O", 3, 3, 3, 3, Type.COMPOUND);
//
//        List<Long> chemicalIDs = new ArrayList<>();
//        chemicalIDs.add(hydrogen.getId());
//        chemicalIDs.add(oxygen.getId());
//
//        MadeOfTableDataGateway.CreateNewCompound(h2o.getId(), chemicalIDs);
//        MadeOfTableDataGateway.CreateNewCompound(hydrogenPeroxide.getId(), chemicalIDs);
//
//        List<MadeOfDTO> list = MadeOfTableDataGateway.findCompoundsWithOneElement(oxygen.getId());
//
//        assertEquals(list.get(0).getChemicalID(), h2o.getId());
//        assertEquals(list.get(1).getChemicalID(), hydrogenPeroxide.getId());
//
//        MadeOfTableDataGateway.deleteCompounds(h2o.getId());
//        MadeOfTableDataGateway.deleteCompounds(hydrogenPeroxide.getId());
//        h2o.delete();
//        hydrogen.delete();
//        oxygen.delete();
//        hydrogenPeroxide.delete();
//    }
//
//    @Test
//    public void testDeleteElement() throws Exception {
//        boolean thrown = false;
//        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 2, 2, Type.ELEMENT);
//        ChemicalRowDataGateway helium = new ChemicalRowDataGateway("Helium", 2, 2, 1, 1, Type.ELEMENT);
//        List<Long> chemicalIDs = new ArrayList<>();
//        chemicalIDs.add(helium.getId());
//
//        MadeOfTableDataGateway test = new MadeOfTableDataGateway();
//        test.CreateNewCompound(hydrogen.getId(), chemicalIDs);
//
//        try {
//            test.deleteElements(helium.getId());
//        } catch (Exception e) {
//            thrown = true;
//        }
//        hydrogen.delete();
//        helium.delete();
//        assertFalse(thrown);
//
//    }
//
//    @Test
//    public void testsDeleteCompound() throws Exception {
//        boolean thrown = false;
//        ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway("Hydrogen", 1, 1, 2, 2, Type.ELEMENT);
//        ChemicalRowDataGateway helium = new ChemicalRowDataGateway("Helium", 2, 2, 1, 1, Type.ELEMENT);
//        ChemicalRowDataGateway water = new ChemicalRowDataGateway("water", 5, 5, 5, 5, Type.ELEMENT);
//
//        List<Long> chemicalIDs = new ArrayList<>();
//        chemicalIDs.add(hydrogen.getId());
//        chemicalIDs.add(helium.getId());
//
//
//        MadeOfTableDataGateway tester = new MadeOfTableDataGateway();
//        tester.CreateNewCompound(water.getId(), chemicalIDs);
//        assertNotNull(water);
//        try {
//            tester.deleteCompounds(water.getId());
//        } catch (Exception e) {
//            fail();
//        }
//        water.delete();
//        hydrogen.delete();
//        helium.delete();
//
//
//    }
//
//}
