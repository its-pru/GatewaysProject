package datasource;

import DTO.BaseDTO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseGatewayTest extends TestCase{

    // Row Data Gateways
    @Test
    public void testCreate() throws Exception{
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new BaseRowDataGateway(testerChem1.getId(), testerChem2.getId());
        assertNotNull(tester);

        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    @Test
    public void testFinder() throws Exception{
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new BaseRowDataGateway(testerChem1.getId(), testerChem2.getId());
        var finder = new BaseRowDataGateway(tester.getId());

        assertEquals(testerChem1.getId(), finder.getId());
        assertEquals(testerChem2.getId(), finder.getSolute());
        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    @Test
    public void testPersist() throws Exception{
        var testerChem1 = new ChemicalRowDataGateway("TestChem1");
        var testerChem2 = new ChemicalRowDataGateway("TestChem2");
        var tester = new BaseRowDataGateway(testerChem1.getId(), testerChem1.getId());

        tester.setSolute(testerChem2.getId());

        tester.persist();

        assertEquals(testerChem2.getId(), tester.getSolute());

        testerChem1.delete(testerChem1.getId());
        testerChem2.delete(testerChem2.getId());

    }

    //Table Data Gateway tests
    @Test
    public void testGetBases() throws Exception{
        ChemicalRowDataGateway chemical1 = new ChemicalRowDataGateway("chemical1");
        ChemicalRowDataGateway chemical2 = new ChemicalRowDataGateway("chemical2");
        ChemicalRowDataGateway chemical3 = new ChemicalRowDataGateway("chemical3");
        ChemicalRowDataGateway chemical4 = new ChemicalRowDataGateway("chemical4");

        BaseRowDataGateway base1 = new BaseRowDataGateway(chemical1.getId(), chemical2.getId());
        BaseRowDataGateway base2 = new BaseRowDataGateway(chemical3.getId(), chemical4.getId());

        List<Long> listOfIDs = new ArrayList<Long>();
        listOfIDs.add(base1.getId());
        listOfIDs.add(base2.getId());
        List<BaseDTO> baseList = BaseTableDataGateway.getBases(listOfIDs);

        assertEquals(base1.getSolute(), baseList.get(0).getSolute());
        assertEquals(base2.getSolute(), baseList.get(1).getSolute());

        chemical1.delete(chemical1.getId());
        chemical2.delete(chemical2.getId());
        chemical3.delete(chemical3.getId());
        chemical4.delete(chemical4.getId());
    }
}
