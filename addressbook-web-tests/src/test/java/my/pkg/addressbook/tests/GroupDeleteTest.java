package my.pkg.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {

    app.gotoGroupPage();
    app.selectGroup();
    app.selectDeleteGroup();

  }

}
