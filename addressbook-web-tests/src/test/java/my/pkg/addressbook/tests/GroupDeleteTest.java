package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTest extends TestBase {

  @Test
  public void testGroupDelete() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList(); // колличество групп до добавления. Список
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().selectDeleteGroup();
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
    Assert.assertEquals (after.size(), before.size() - 1); // Проверка на колличество групп до и после добавления

  }

}
