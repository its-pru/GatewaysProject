package datasource;

import exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

public class CompoundRowDataGatewayTest extends TestCase {

    @Test
    public void testCreate() throws Exception {
        CompoundRowDataGateway testCreate = null;
        try {
            testCreate = new CompoundRowDataGateway(7, "Rick");
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotNull(testCreate);
        testCreate.delete(testCreate.getName());
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
        newElement.delete(newElement.getName());
        testFind.delete(testFind.getName());

    }


    @Test
    public void testExists() throws Exception {
        CompoundRowDataGateway testExist = new CompoundRowDataGateway(16, "Tom");
        assertNotNull(testExist);

        boolean exists = testExist.exists("Tom");
        assertTrue(exists);

        exists = testExist.exists("Rick");
        assertFalse(exists);

        testExist.delete(testExist.getName());

    }

    @Test
    public void testDeleteCompound() throws Exception {
        CompoundRowDataGateway testDelete = new CompoundRowDataGateway(5, "Timmy");
        boolean success = testDelete.delete(testDelete.getName());
        assertEquals(true, success);

    }

    @Test
    public void testPersists() throws Exception {
        CompoundRowDataGateway testPersists = new CompoundRowDataGateway(25, "Frank");
        testPersists.setName("Don");
        testPersists.setName("Tony");

        testPersists.delete(testPersists.getName());

    }
}
