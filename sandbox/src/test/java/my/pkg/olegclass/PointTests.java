package my.pkg.olegclass;

import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testArea () {
        Point p1 = new Point (1,5);
        Point p2 = new Point (2,8);
                assert p1.distance(p2) == 34;
    }
}
