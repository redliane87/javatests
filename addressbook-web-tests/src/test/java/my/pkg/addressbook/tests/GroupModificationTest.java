package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification () {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList(); // колличество групп до добавления. Список
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test004", "test002", "test003"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
        Assert.assertEquals (after.size(), before.size()); // Проверка на колличество групп до и после добавления
    }
}
