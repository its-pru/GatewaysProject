package datasource;

import DTO.ElementDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ElementTableDataGatewayTests extends TestCase {
    @Test
    public void testGetElements() throws Exception {
        ElementRowDataGateway element1 = new ElementRowDataGateway(1,"Rick", 1, 1);
        ElementRowDataGateway element2 = new ElementRowDataGateway(2,"Don", 2, 2);

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(element1.getID());
        listOfIDs.add(element2.getID());
        List<ElementDTO> elementList = ElementTableDataGateway.getElements(listOfIDs);

        assertEquals(element1.getAtomicMass(), elementList.get(0).getAtomicMass());
        assertEquals(element2.getAtomicMass(), elementList.get(1).getAtomicMass());

        element1.delete(element1.getName());
        element2.delete(element2.getName());
    }
}
