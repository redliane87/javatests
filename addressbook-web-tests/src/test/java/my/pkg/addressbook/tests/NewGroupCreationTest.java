package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.*;

public class NewGroupCreationTest extends TestBase {

  @Test
  public void testNewGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test001", null, null
    ));
    app.getGroupHelper().submitGroupCretion();
  }

}
