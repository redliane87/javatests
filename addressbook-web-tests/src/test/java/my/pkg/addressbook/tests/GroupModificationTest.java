package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


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

        Groups before = app.group().all(); // колличество групп до добавления. Список
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test001").withHeader("test002").withFooter("test003");
        app.group().modify(group);
        app.goTo().groupPage();
        Groups after = app.group().all(); // Колличество групп после добавления. Список
        assertEquals(after.size(), before.size()); // Проверка на колличество групп до и после добавления
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }


}
