package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all(); // колличество групп до добавления. Список
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all(); // Колличество групп после добавления. Список
        assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));// Проверка на колличество групп до и после добавления
        assertThat(after, equalTo(before.without(deletedGroup)));

    }


}
