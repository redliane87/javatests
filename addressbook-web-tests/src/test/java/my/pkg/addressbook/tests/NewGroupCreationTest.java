package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewGroupCreationTest extends TestBase {

  @Test
  public void testNewGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); // колличество групп до добавления
    app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
    int after = app.getGroupHelper().getGroupCount(); // Колличество групп после добавления
    Assert.assertEquals (after, before + 1); // Проверка на колличество групп до и после добавления
  }

}
