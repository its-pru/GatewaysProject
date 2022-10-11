package datasource;

import exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

public class CompoundRowDataGatewayTest {

    @Test
    public void testCreate() throws Exception {
        CompoundRowDataGateway testCreate = null;
        try {
            testCreate = new CompoundRowDataGateway(7, "Rick");
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotNull(testCreate);
        testCreate.delete(testCreate.getID());
    }
    @Test
    public void testFindCompound() throws Exception {
        boolean success = true;
        CompoundRowDataGateway newElement = new CompoundRowDataGateway(15,"Don");

        CompoundRowDataGateway testFind = null;
        try {
            testFind = new CompoundRowDataGateway(newElement.getID());

        } catch (SQLException e) {
            success = false;
        }

        assertTrue(success);
        newElement.delete(newElement.getID());
        testFind.delete(testFind.getID());

    }


    @Test
    public void testExists() throws Exception {
        CompoundRowDataGateway testExist = new CompoundRowDataGateway(16, "Tom");
        assertNotNull(testExist);

        boolean exists = testExist.exists("Tom", 16, 18);
        assertTrue(exists);

        exists = testExist.exists("FakeEntry", 3, 1);
        assertFalse(exists);

        testExist.delete(testExist.getID());

    }

    @Test
    public void testDeleteCompound() throws Exception {
        CompoundRowDataGateway testDelete = new CompoundRowDataGateway(5, "Timmy");
        boolean success = testDelete.delete(testDelete.getID());
        assertEquals(true, success);

    }

    @Test
    public void testPersists() throws Exception {
        CompoundRowDataGateway testPersists = new CompoundRowDataGateway(25, "Frank");
        testPersists.setName("Don");
        testPersists.setName("Tony");

        testPersists.delete(testPersists.getID());

    }
}
