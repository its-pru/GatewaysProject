package datasource;

import DTO.AcidDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AcidGatewayTest extends TestCase {
    // Row Data Gateways
    @Test
    public void testCreate() throws Exception {
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        AcidRowDataGateway tester = new AcidRowDataGateway(testerChem1.getId(), testerChem2.getId());
        assertNotNull(tester);
        testerChem1.delete();
        testerChem2.delete();

    }

    @Test
    public void testFinder() throws Exception {
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        AcidRowDataGateway tester = new AcidRowDataGateway(testerChem1.getId(), testerChem2.getId());
        AcidRowDataGateway finder = new AcidRowDataGateway(tester.getId());
        assertEquals(testerChem1.getId(), finder.getId());
        assertEquals(testerChem2.getId(), finder.getSolute());
        testerChem1.delete();
        testerChem2.delete();

    }

    @Test
    public void testPersist() throws Exception {
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem1");
        ChemicalRowDataGateway testerChem2 = ChemicalRowDataGateway.createChemicalRowDataGateway("TestChem2");
        AcidRowDataGateway tester = new AcidRowDataGateway(testerChem1.getId(), testerChem1.getId());
        tester.setSolute(testerChem2.getId());
        tester.persist();
        assertEquals(testerChem2.getId(), tester.getSolute());
        testerChem1.delete();
        testerChem2.delete();

    }

    // Table Data Gateway tests

    @Test
    public void testGetAcids() throws Exception {
        ChemicalRowDataGateway chemical1 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical3");
        ChemicalRowDataGateway chemical4 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical4");

        AcidRowDataGateway acid1 = new AcidRowDataGateway(chemical1.getId(), chemical2.getId());
        AcidRowDataGateway acid2 = new AcidRowDataGateway(chemical3.getId(), chemical4.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(acid1.getId());
        listOfIDs.add(acid2.getId());
        List<AcidDTO> acidList = AcidTableDataGateway.getAcids(listOfIDs);

        assertEquals(acid1.getSolute(), acidList.get(0).getSolute());
        assertEquals(acid2.getSolute(), acidList.get(1).getSolute());

        chemical1.delete();
        chemical2.delete();
        chemical3.delete();
        chemical4.delete();
    }

}
