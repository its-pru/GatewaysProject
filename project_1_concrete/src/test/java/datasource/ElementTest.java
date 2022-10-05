package datasource;

import DTO.ElementDTO;
import junit.framework.TestCase;
import org.junit.Test;

public class ElementTest extends TestCase{

    @Test
    public void test() {
        ElementDTO x = new ElementDTO(1,"test",1,1.1);
        assertEquals(1,x.getID());
    }
}
