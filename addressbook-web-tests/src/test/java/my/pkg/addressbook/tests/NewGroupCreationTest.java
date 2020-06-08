package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class NewGroupCreationTest extends TestBase {

  @Test
  public void testNewGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList(); // колличество групп до добавления. Список
    app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
    List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
    Assert.assertEquals (after.size(), before.size() + 1); // Проверка на колличество групп до и после добавления
  }

}
