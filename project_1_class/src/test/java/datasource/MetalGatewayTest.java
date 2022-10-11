package datasource;

import DTO.AcidDTO;
import DTO.ElementDTO;
import DTO.MetalDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MetalGatewayTest extends TestCase {
    // Row Data Gateways
    @Test
    public void testCreate() throws Exception {
        var testSolute = new ChemicalRowDataGateway("Solute");
        var testChem = new ChemicalRowDataGateway("DissolvesLead");
        var testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());
        var leadChem = new ChemicalRowDataGateway("Lead");
        var leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        var lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());

        assertNotNull(lead);
        testSolute.delete(testSolute.getId());
        testChem.delete(testChem.getId());
        leadChem.delete(leadChem.getId());
    }

    @Test
    public void testFinder() throws Exception {
        var testSolute = new ChemicalRowDataGateway("Solute");
        var testChem = new ChemicalRowDataGateway("DissolvesLead");
        var testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());
        var leadChem = new ChemicalRowDataGateway("Lead");
        var leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        var lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());
        var finder = new MetalRowDataGateway(lead.getId());

        assertEquals(lead.getId(), finder.getId());
        assertEquals(lead.getDissolvedBy(), finder.getDissolvedBy());
        assertEquals(testAcid.getId(), finder.getDissolvedBy());

        testSolute.delete(testSolute.getId());
        testChem.delete(testChem.getId());
        leadChem.delete(leadChem.getId());

    }

    @Test
    public void testPersist() throws Exception {
        var testSolute = new ChemicalRowDataGateway("Solute");
        var testChem = new ChemicalRowDataGateway("DissolvesLead");
        var testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());

        var newChem = new ChemicalRowDataGateway("DissolvesLeadToo");
        var newAcid = new AcidRowDataGateway(newChem.getId(), testSolute.getId());

        var leadChem = new ChemicalRowDataGateway("Lead");
        var leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        var lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());

        lead.setDissolvedBy(newAcid.getId());
        lead.persist();
        assertEquals(newAcid.getId(), lead.getDissolvedBy());

        testSolute.delete(testSolute.getId());
        testChem.delete(testChem.getId());
        newChem.delete(newChem.getId());
        leadChem.delete(leadChem.getId());

    }

    //Table Data Gateway tests

    @Test
    public void testGetMetals() throws Exception {
        ChemicalRowDataGateway chemical1 = new ChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = new ChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = new ChemicalRowDataGateway("chemical3");

        ElementRowDataGateway element1 = new ElementRowDataGateway(chemical1.getId(), 1, 1.0);
        ElementRowDataGateway element2 = new ElementRowDataGateway(chemical2.getId(), 2, 2.0);

        AcidRowDataGateway acid1 = new AcidRowDataGateway(chemical3.getId(), chemical2.getId());

        MetalRowDataGateway metal = new MetalRowDataGateway(element1.getId(), acid1.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(metal.getId());
        List<MetalDTO> elementList = MetalTableDataGateway.getMetals(listOfIDs);

        assertEquals(metal.getId(), elementList.get(0).getID());
        assertEquals(metal.getDissolvedBy(), chemical3.getId());

        chemical1.delete(chemical1.getId());
        chemical2.delete(chemical2.getId());
        chemical3.delete(chemical3.getId());
    }
}
