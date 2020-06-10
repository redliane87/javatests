package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class NewGroupCreationTest extends TestBase {

    @Test
    public void testNewGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList(); // колличество групп до добавления. Список
        GroupData group = new GroupData("test001", null, null);
        app.getGroupHelper().creatGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size() + 1); // Проверка на колличество групп до и после добавления


        int max = 0;
        for (GroupData q : after) {
            if (q.getId() > max) {
                max = q.getId();
            }
        }
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
