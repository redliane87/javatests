package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void testGroupDelete() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); // колличество групп до добавления
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().selectDeleteGroup();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupHelper().getGroupCount(); // Колличество групп после добавления
    Assert.assertEquals (after, before - 1); // Проверка на колличество групп до и после добавления

  }

}
