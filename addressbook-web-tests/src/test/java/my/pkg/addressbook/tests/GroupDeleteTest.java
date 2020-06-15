package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test001"));
        }

    }
    @Test
    public void testGroupDelete() {
        Set<GroupData> before = app.group().all(); // колличество групп до добавления. Список
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all(); // Колличество групп после добавления. Список
        Assert.assertEquals(after.size(), before.size() - 1); // Проверка на колличество групп до и после добавления

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);// Сравниваем элементы с одинаковыми индексами

    }



}
