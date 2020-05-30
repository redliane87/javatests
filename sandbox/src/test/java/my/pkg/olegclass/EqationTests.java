package my.pkg.olegclass;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EqationTests {
    @Test
    public void  test0 () {
        Eqation e = new Eqation(1,1,1);
           Assert.assertEquals(e.rootNumber(), 0);
            }
    @Test
    public void  test1 () {
        Eqation e = new Eqation(1,2,1);
        Assert.assertEquals(e.rootNumber(), 1);
        }
    @Test
    public void  test2() {
        Eqation e = new Eqation(1,5,6);
        Assert.assertEquals(e.rootNumber(), 2);
    }
    @Test
    public void  testLiner() {
        Eqation e = new Eqation(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }
    @Test
    public void  testConstant() {
        Eqation e = new Eqation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }
    @Test
    public void  testZero() {
        Eqation e = new Eqation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }
}
