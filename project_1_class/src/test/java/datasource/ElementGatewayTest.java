package datasource;

import DTO.ElementDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ElementGatewayTest extends TestCase{

    // Row Data Gateways
    @Test
    public void testCreate() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("Potassium");
        ElementRowDataGateway tester = new ElementRowDataGateway(testerChem1.getId(), 19, 39.0983);
        assertNotNull(tester);

        testerChem1.delete();
    }

    @Test
    public void testFinder() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("Potassium");
        ElementRowDataGateway tester = new ElementRowDataGateway(testerChem1.getId(), 19, 39.0983);
        ElementRowDataGateway finder = new ElementRowDataGateway(tester.getId());

        assertEquals(testerChem1.getId(), finder.getId());
        assertEquals(tester.getAtomicNumber(), finder.getAtomicNumber());
        assertEquals(tester.getAtomicMass(), tester.getAtomicMass());

        testerChem1.delete();

    }

    @Test
    public void testPersist() throws Exception{
        ChemicalRowDataGateway testerChem1 = ChemicalRowDataGateway.createChemicalRowDataGateway("Potassium");
        ElementRowDataGateway tester = new ElementRowDataGateway(testerChem1.getId(), 19, 39.0983);
        tester.setAtomicNumber(1);
        tester.setAtomicMass(1.1);

        tester.persist();

        assertEquals(tester.getAtomicNumber(), 1);
        assertEquals(tester.getAtomicMass(), 1.1);

        testerChem1.delete();
    }


//Table Data Gateway tests
    @Test
    public void testGetElements() throws Exception {
        ChemicalRowDataGateway chemical1 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = ChemicalRowDataGateway.createChemicalRowDataGateway("chemical2");

        ElementRowDataGateway element1 = new ElementRowDataGateway(chemical1.getId(), 1, 1);
        ElementRowDataGateway element2 = new ElementRowDataGateway(chemical2.getId(), 2, 2);

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(element1.getId());
        listOfIDs.add(element2.getId());
        List<ElementDTO> elementList = ElementTableDataGateway.getElements(listOfIDs);

        assertEquals(element1.getAtomicMass(), elementList.get(0).getAtomicMass());
        assertEquals(element2.getAtomicMass(), elementList.get(1).getAtomicMass());

        chemical1.delete();
        chemical2.delete();
    }
}
