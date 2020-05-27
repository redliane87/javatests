package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.*;

public class NewGroupCreationTest extends TestBase {

  @Test
  public void testNewGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test001", "test002", "test003"));
    app.submitGroupCretion();
  }

}
