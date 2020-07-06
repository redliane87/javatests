package my.pkg.addressbook.tests;

import my.pkg.addressbook.TestBase;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test001"));
        }
    }

    @Test
    public void testGroupDelete() {
        Groups before = app.db().groups(); // колличество групп до добавления. Список
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups(); // Колличество групп после добавления. Список
        assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));// Проверка на колличество групп до и после добавления
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }


}
