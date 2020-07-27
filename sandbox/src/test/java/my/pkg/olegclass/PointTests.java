package my.pkg.olegclass;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testArea () {
        Point p1 = new Point (1,2);
        Point p2 = new Point (5,5);
        Assert.assertEquals(p1.distance(p2),7);
    }
}
