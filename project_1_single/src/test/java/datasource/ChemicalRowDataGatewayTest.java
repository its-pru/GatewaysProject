package datasource;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;

public class ChemicalRowDataGatewayTest extends TestCase {
    @Test
    public void testCreate(){
        ChemicalRowDataGateway testGateway = new ChemicalRowDataGateway();
        assertNotNull(testGateway);
    }
    @Test
    public void testJDBCConnect(){
        ChemicalRowDataGateway testGateway = new ChemicalRowDataGateway();


    }
}