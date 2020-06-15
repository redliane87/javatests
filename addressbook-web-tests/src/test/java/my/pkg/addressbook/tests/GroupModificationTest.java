package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test001"));
        }

    }

    @Test
    public void testGroupModification() {

        Set<GroupData> before = app.group().all(); // колличество групп до добавления. Список
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test001").withHeader("test002").withFooter("test003");
        app.group().modify(group);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size()); // Проверка на колличество групп до и после добавления

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }


}
