package datasource;

import junit.framework.TestCase;
import org.junit.Test;

public class MetalRowDataGatewayTest extends TestCase {

    @Test
    public void testCreate() throws Exception{
        MetalRowDataGateway tester = null;
        tester = new MetalRowDataGateway(1, "Tim", 12,10,5);
        assertNotNull(tester);
        tester.delete(1);
    }

    @Test
    public void testFinder() throws Exception{
        MetalRowDataGateway gateway = new MetalRowDataGateway(3, "Tommy", 15,12,2);
        MetalRowDataGateway tester = new MetalRowDataGateway(3);

        assertEquals("Tommy",tester.getName());
        gateway.delete(3);

    }

    @Test
    public void testPersist() throws Exception{
        MetalRowDataGateway tester = new MetalRowDataGateway(2,"Evan", 65,6,6);
        tester.setName("Rick");
        tester.setAtomicMass(2);
        tester.setDissolvedBy(7);
        tester.setAtomicNumber(1);
        tester.persist();
        assertEquals("Rick",tester.getName());
        tester.delete(2);
    }

    @Test
    public void testDelete() throws Exception{
        MetalRowDataGateway tester = new MetalRowDataGateway(5,"Andrew", 7,7,3);
        boolean worked = tester.delete(5);
        assertEquals(true, worked);
    }

    @Test
    public void testExsist() throws Exception{
        MetalRowDataGateway tester = new MetalRowDataGateway(1, "Luke", 15,1,8);
        assertNotNull(tester);

        assertTrue(tester.exists("Luke",15,1));
        assertFalse(tester.exists("Tom",12,5));

        tester.delete(1);
    }

}
