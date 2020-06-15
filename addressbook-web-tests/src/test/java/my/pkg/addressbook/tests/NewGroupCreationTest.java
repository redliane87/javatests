package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewGroupCreationTest extends TestBase {

    @Test
    public void testNewGroupCreation() {

        app.goTo().groupPage();
        Set<GroupData> before = app.group().all(); // колличество групп до добавления. Список
        GroupData group = new GroupData().withName("test003");
        app.group().create(group);
        Set<GroupData> after = app.group().all(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size() + 1); // Проверка на колличество групп до и после добавления



        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
