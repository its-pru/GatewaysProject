package datasource;

import junit.framework.TestCase;
import org.junit.Test;

public class BaseRowDataGatewayTest extends TestCase {

    @Test
    public void testCreate() throws Exception{
        BaseRowDataGateway tester = null;
        tester = new BaseRowDataGateway(1, "Tim", 12);
        assertNotNull(tester);
        tester.delete(1);
    }

    @Test
    public void testFinder() throws Exception{
        BaseRowDataGateway gateway = new BaseRowDataGateway(1, "Tommy", 15);
        BaseRowDataGateway tester = new BaseRowDataGateway(1);

        assertEquals("Tommy",tester.getName());
        gateway.delete(1);

    }

    @Test
    public void testPersist() throws Exception{
        BaseRowDataGateway tester = new BaseRowDataGateway(2,"Evan", 6);
        tester.setName("Rick");
        tester.setSolute(5);
        tester.persist();
        assertEquals("Rick",tester.getName());
        tester.delete(2);
    }

    @Test
    public void testDelete() throws Exception{
        BaseRowDataGateway tester = new BaseRowDataGateway(5,"Andrew", 7);
        boolean worked = tester.delete(5);
        assertEquals(true, worked);
    }

    @Test
    public void testExsist() throws Exception{
        BaseRowDataGateway tester = new BaseRowDataGateway(1, "Luke", 15);
        assertNotNull(tester);

        assertTrue(tester.exists("Luke"));
        assertFalse(tester.exists("Tom"));

        tester.delete(1);
    }

}
