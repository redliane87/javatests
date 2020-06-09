package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
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
        GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test001", "test002", "test003");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
        Assert.assertEquals (after.size(), before.size()); // Проверка на колличество групп до и после добавления

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
