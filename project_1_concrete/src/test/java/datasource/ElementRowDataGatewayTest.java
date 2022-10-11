package datasource;

import exceptions.AlreadyExistsException;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ElementRowDataGatewayTest extends TestCase {
    @Test
    public void testCreate() throws Exception {
        ElementRowDataGateway testCreate = null;
        try {
            testCreate = new ElementRowDataGateway(7, "Rick", 1, 10);
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotNull(testCreate);
        testCreate.delete(testCreate.getID());
    }
    @Test
    public void testFindElement() throws Exception {
        boolean success = true;
        ElementRowDataGateway newElement = new ElementRowDataGateway(15,"Don", 100, 50);

        ElementRowDataGateway testFind = null;
        try {
            testFind = new ElementRowDataGateway(newElement.getID());

        } catch (SQLException e) {
            success = false;
        }

        assertTrue(success);
        newElement.delete(newElement.getID());
        testFind.delete(testFind.getID());

    }


    @Test
    public void testExists() throws Exception {
        ElementRowDataGateway testExist = new ElementRowDataGateway(16, "Tom", 16, 18);
        assertNotNull(testExist);

        boolean exists = testExist.exists("Tom", 16, 18);
        assertTrue(exists);

        exists = testExist.exists("FakeEntry", 3, 1);
        assertFalse(exists);

        testExist.delete(testExist.getID());

    }

    @Test
    public void testDeleteElement() throws Exception {
        ElementRowDataGateway testDelete = new ElementRowDataGateway(5, "Timmy", 10, 10);
        boolean success = testDelete.delete(testDelete.getID());
        assertEquals(true, success);

    }

    @Test
    public void testPersists() throws Exception {
        ElementRowDataGateway testPersists = new ElementRowDataGateway(25, "Frank", 4, 1);
        testPersists.setName("Don");
        testPersists.setAtomicNumber(3);
        testPersists.setAtomicMass(12);
        testPersists.persist();
        assertEquals(true,testPersists.exists("Don", 3,12));
        testPersists.delete(testPersists.getID());

    }
}



