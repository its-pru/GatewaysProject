package datasource;

import junit.framework.TestCase;
import org.junit.Test;

public class AcidRowDataGatewayTest extends TestCase {

    @Test
    public void testCreate() throws Exception{
            AcidRowDataGateway tester = null;
            tester = new AcidRowDataGateway(1, "Tim", 12);
            assertNotNull(tester);
            tester.delete(1);
        }

    @Test
    public void testFinder() throws Exception{
        AcidRowDataGateway gateway = new AcidRowDataGateway(1, "Tommy", 15);
        AcidRowDataGateway tester = new AcidRowDataGateway(1);

        assertEquals("Tommy",tester.getName());
        gateway.delete(1);

    }

    @Test
    public void testPersist() throws Exception{
        AcidRowDataGateway tester = new AcidRowDataGateway(2,"Evan", 6);
        tester.setName("Rick");
        tester.setSolute(5);
        tester.persist();
        assertEquals("Rick",tester.getName());
        tester.delete(2);
    }

    @Test
    public void testDelete() throws Exception{
        AcidRowDataGateway tester = new AcidRowDataGateway(5,"Andrew", 7);
        boolean worked = tester.delete(5);
        assertEquals(true, worked);
    }

    @Test
    public void testExsist() throws Exception{
        AcidRowDataGateway tester = new AcidRowDataGateway(1, "Luke", 15);
        assertNotNull(tester);

        assertTrue(tester.exists("Luke"));
        assertFalse(tester.exists("Tom"));

        tester.delete(1);
    }

}
