package datasource;

import Exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

public class ChemicalRowDataGatewayTest extends TestCase {

    @Test
    public void testCreate() throws Exception {
        ChemicalRowDataGateway testCreate = null;
        try {
            testCreate = new ChemicalRowDataGateway("Joe", 1000, 1000, 1000, 1000, Type.ACID);
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotNull(testCreate);
        testCreate.delete();
    }

    @Test
    public void testFindChemical() throws Exception {
        boolean success = true;
        ChemicalRowDataGateway newChemical = new ChemicalRowDataGateway("Keyboard", 100000, 100000, 10000000, 100000000, Type.BASE);

        ChemicalRowDataGateway testFind = null;
        try {
            testFind = new ChemicalRowDataGateway(newChemical.getId());

        } catch (SQLException e) {
            success = false;
        }

        assertTrue(success);
        newChemical.delete();
        testFind.delete();

    }


    @Test
    public void testExists() throws Exception {
        ChemicalRowDataGateway testExist = new ChemicalRowDataGateway("TestExists", 100, 1200, 10, 10, Type.ELEMENT);
        assertNotNull(testExist);

        boolean exists = testExist.exists("TestExists", 100, 1200, 10, 10);
        assertTrue(exists);

        exists = testExist.exists("FakeEntry", 1, 1, 1, 1);
        assertFalse(exists);

        testExist.delete();

    }

    @Test
    public void testDeleteChemical() throws Exception {
        ChemicalRowDataGateway testDelete = new ChemicalRowDataGateway("TestChemical", 10, 10, 10, 10, Type.METAL);
        boolean success = testDelete.delete();
        assertEquals(true, success);

    }

    @Test
    public void testAlreadyExistingChemical() throws Exception {
        boolean thrown = false;
        ChemicalRowDataGateway chemical = null;

        try {
            chemical = new ChemicalRowDataGateway("chemical2", 101, 1201, 5, 6, Type.COMPOUND);
        } catch (AlreadyExistsException alreadyExists) {
            thrown = true;
        }

        assertFalse(thrown);

        try {
            chemical = new ChemicalRowDataGateway("chemical2", 101, 1201, 5, 6, Type.ACID);
        } catch (AlreadyExistsException alreadyExists) {
            thrown = true;
        }

        chemical.delete();

        assertTrue(thrown);

    }

    @Test
    public void testPersists() throws Exception {
        ChemicalRowDataGateway testPersists = new ChemicalRowDataGateway("MouseAdapter", 1023420, 108943, 12354135, 134513, Type.COMPOUND);
        testPersists.setName("HAHA");
        testPersists.setAtomicNumber(46253);
        testPersists.setAtomicMass(420);
        testPersists.setSolute(0);
        testPersists.setDissolvedBy(80085);
        testPersists.persist();

        ChemicalRowDataGateway findPersists = new ChemicalRowDataGateway(testPersists.getId());
        assertEquals(findPersists.getName(), "HAHA");
        assertEquals(findPersists.getAtomicNumber(), 46253);
        assertEquals(findPersists.getAtomicMass(), 420.0);
        assertEquals(findPersists.getSolute(), 0);
        assertEquals(testPersists.getDissolvedBy(), 80085);

        testPersists.delete();
        findPersists.delete();

    }


}