package datasource;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotNull;

public class ElementRowDataGatewayTest extends TestCase {
    public void testCreate() throws Exception{
        ElementRowDataGateway tester = new ElementRowDataGateway(1, "Evan", 1, 1);
        assertNotNull(tester);
    }
}
