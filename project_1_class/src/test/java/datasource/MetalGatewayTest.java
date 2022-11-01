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
        ChemicalRowDataGateway testSolute = ChemicalRowDataGateway.createChemicalRowDataGateway("Solute");
        ChemicalRowDataGateway testChem = ChemicalRowDataGateway.createChemicalRowDataGateway("DissolvesLead");
        AcidRowDataGateway testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());
        ChemicalRowDataGateway leadChem = ChemicalRowDataGateway.createChemicalRowDataGateway("Lead");
        ElementRowDataGateway leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        MetalRowDataGateway lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());

        assertNotNull(lead);
        testSolute.delete();
        testChem.delete();
        leadChem.delete();
    }

    @Test
    public void testFinder() throws Exception {
        ChemicalRowDataGateway testSolute = ChemicalRowDataGateway.createChemicalRowDataGateway("Solute");
        ChemicalRowDataGateway testChem = ChemicalRowDataGateway.createChemicalRowDataGateway("DissolvesLead");
        AcidRowDataGateway testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());
        ChemicalRowDataGateway leadChem = ChemicalRowDataGateway.createChemicalRowDataGateway("Lead");
        ElementRowDataGateway leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        MetalRowDataGateway lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());
        MetalRowDataGateway finder = new MetalRowDataGateway(lead.getId());

        assertEquals(lead.getId(), finder.getId());
        assertEquals(lead.getDissolvedBy(), finder.getDissolvedBy());
        assertEquals(testAcid.getId(), finder.getDissolvedBy());

        testSolute.delete();
        testChem.delete();
        leadChem.delete();

    }

    @Test
    public void testPersist() throws Exception {
        ChemicalRowDataGateway testSolute = ChemicalRowDataGateway.createChemicalRowDataGateway("Solute");
        ChemicalRowDataGateway testChem = ChemicalRowDataGateway.createChemicalRowDataGateway("DissolvesLead");
        AcidRowDataGateway testAcid = new AcidRowDataGateway(testChem.getId(), testSolute.getId());

        ChemicalRowDataGateway newChem = ChemicalRowDataGateway.createChemicalRowDataGateway("DissolvesLeadToo");
        AcidRowDataGateway newAcid = new AcidRowDataGateway(newChem.getId(), testSolute.getId());

        ChemicalRowDataGateway leadChem = ChemicalRowDataGateway.createChemicalRowDataGateway("Lead");
        ElementRowDataGateway leadElement = new ElementRowDataGateway(leadChem.getId(), 82, 207.2);
        MetalRowDataGateway lead = new MetalRowDataGateway(leadElement.getId(), testAcid.getId());

        lead.setDissolvedBy(newAcid.getId());
        lead.persist();
        assertEquals(newAcid.getId(), lead.getDissolvedBy());

        testSolute.delete();
        testChem.delete();
        newChem.delete();
        leadChem.delete();

    }

    //Table Data Gateway tests

    @Test
    public void testGetMetals() throws Exception {
        ChemicalRowDataGateway chemical1 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical3");

        ElementRowDataGateway element1 = new ElementRowDataGateway(chemical1.getId(), 1, 1.0);
        ElementRowDataGateway element2 = new ElementRowDataGateway(chemical2.getId(), 2, 2.0);

        AcidRowDataGateway acid1 = new AcidRowDataGateway(chemical3.getId(), chemical2.getId());

        MetalRowDataGateway metal = new MetalRowDataGateway(element1.getId(), acid1.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(metal.getId());
        List<MetalDTO> elementList = MetalTableDataGateway.getMetals(listOfIDs);

        assertEquals(metal.getId(), elementList.get(0).getID());
        assertEquals(metal.getDissolvedBy(), chemical3.getId());

        chemical1.delete();
        chemical2.delete();
        chemical3.delete();
    }
}
