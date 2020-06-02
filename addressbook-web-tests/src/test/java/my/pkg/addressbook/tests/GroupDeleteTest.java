package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void testGroupDelete() {

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().selectDeleteGroup();

  }

}
