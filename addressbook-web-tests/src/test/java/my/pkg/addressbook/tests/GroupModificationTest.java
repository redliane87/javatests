package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
        }

    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.getGroupHelper().getGroupList(); // колличество групп до добавления. Список
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "test001", "test002", "test003");
        app.getGroupHelper().modifyGroup(index, group);
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size()); // Проверка на колличество групп до и после добавления

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
