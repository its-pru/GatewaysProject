package datasource;
import java.util.ArrayList;
import java.util.List;
import DTO.ChemicalDTO;

import junit.framework.TestCase;
import org.junit.Test;

public class ChemicalGatewayTest extends TestCase {
    // Row Data Gateways
    @Test
    public void testCreate() throws Exception{
        ChemicalRowDataGateway tester = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem");
        assertNotNull(tester);
        tester.delete();
    }

    @Test
    public void testFinder() throws Exception{
        ChemicalRowDataGateway tester = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem");
        ChemicalRowDataGateway finder = new ChemicalRowDataGateway(tester.getName());

        assertEquals("TestChem", finder.getName());

        tester.delete();
    }

    @Test
    public void testPersist() throws Exception{
        ChemicalRowDataGateway tester = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem");
        tester.setName("NewName");
        tester.persist();
        assertEquals("NewName",tester.getName());
        tester.delete();
    }

    @Test
    public void testDelete() throws Exception{
        ChemicalRowDataGateway tester = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem");
        boolean worked = tester.delete();
        assertEquals(true, worked);
    }
    // Table Data Gateways
    @Test
    public void testGetChemicals() throws Exception {
        ChemicalRowDataGateway chemical1 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical2");

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(chemical1.getId());
        listOfIDs.add(chemical2.getId());
        List<ChemicalDTO> chemicalList = ChemicalTableDataGateway.getChemicals(listOfIDs);

        assertEquals(chemical1.getName(), chemicalList.get(0).getName());
        assertEquals(chemical2.getName(), chemicalList.get(1).getName());

         chemical1.delete();
         chemical2.delete();

    }

}
