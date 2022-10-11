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
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new AcidRowDataGateway(testerChem1.getId(), testerChem2.getId());
        assertNotNull(tester);
        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    @Test
    public void testFinder() throws Exception {
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new AcidRowDataGateway(testerChem1.getId(), testerChem2.getId());
        var finder = new AcidRowDataGateway(tester.getId());
        assertEquals(testerChem1.getId(), finder.getId());
        assertEquals(testerChem2.getId(), finder.getSolute());
        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    @Test
    public void testPersist() throws Exception {
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new AcidRowDataGateway(testerChem1.getId(), testerChem1.getId());
        tester.setSolute(testerChem2.getId());
        tester.persist();
        assertEquals(testerChem2.getId(), tester.getSolute());
        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    // Table Data Gateway tests

    @Test
    public void testGetAcids() throws Exception {
        ChemicalRowDataGateway chemical1 = new ChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = new ChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = new ChemicalRowDataGateway("chemical3");
        ChemicalRowDataGateway chemical4 = new ChemicalRowDataGateway("chemical4");

        AcidRowDataGateway acid1 = new AcidRowDataGateway(chemical1.getId(), chemical2.getId());
        AcidRowDataGateway acid2 = new AcidRowDataGateway(chemical3.getId(), chemical4.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(acid1.getId());
        listOfIDs.add(acid2.getId());
        List<AcidDTO> acidList = AcidTableDataGateway.getAcids(listOfIDs);

        assertEquals(acid1.getSolute(), acidList.get(0).getSolute());
        assertEquals(acid2.getSolute(), acidList.get(1).getSolute());

        chemical1.delete(chemical1.getId());
        chemical2.delete(chemical2.getId());
        chemical3.delete(chemical3.getId());
        chemical4.delete(chemical4.getId());
    }

}
