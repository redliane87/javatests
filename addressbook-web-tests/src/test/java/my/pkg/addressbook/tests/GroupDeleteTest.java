package my.pkg.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void testGroupDelete() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().selectDeleteGroup();

  }

}
