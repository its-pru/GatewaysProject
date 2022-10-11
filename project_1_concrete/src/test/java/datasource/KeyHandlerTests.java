package datasource;

import junit.framework.TestCase;
import org.junit.Test;
public class KeyHandlerTests extends TestCase {
    @Test
    public void testGetKey() throws Exception {
        long currentKey = KeyHandler.getCurrentKey();
        long getNewKey = KeyHandler.getNewKey();

        assertTrue(currentKey == (getNewKey - 1));
    }
}
