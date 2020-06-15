package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test001", null, null));
        }

    }
    @Test
    public void testGroupDelete() {
        List<GroupData> before = app.group().list(); // колличество групп до добавления. Список
        int index = before.size() - 1;
        app.group().delete(index);
        app.goTo().groupPage();
        List<GroupData> after = app.group().list(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size() - 1); // Проверка на колличество групп до и после добавления

        before.remove(index);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);// Сравниваем элементы с одинаковыми индексами

    }



}
