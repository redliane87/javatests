package my.pkg.addressbook.tests;

import org.testng.annotations.*;

public class NewGroupCreationTest extends TestBase {

  @Test
  public void testNewGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test001", "test002", "test003"));
    submitGroupCretion();
  }

}
