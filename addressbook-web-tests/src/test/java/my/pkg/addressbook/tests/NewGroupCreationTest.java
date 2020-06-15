package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.Comparator;
import java.util.List;

public class NewGroupCreationTest extends TestBase {

    @Test
    public void testNewGroupCreation() {

        app.goTo().groupPage();
        List<GroupData> before = app.group().list(); // колличество групп до добавления. Список
        GroupData group = new GroupData("test003", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().list(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size() + 1); // Проверка на колличество групп до и после добавления


        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
