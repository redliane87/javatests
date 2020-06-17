package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroupCreationTest extends TestBase {

    @Test
    public void testNewGroupCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all(); // колличество групп до добавления. Список
        GroupData group = new GroupData().withName("test003");
        app.group().create(group);
        Groups after = app.group().all(); // Колличество групп после добавления. Список
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1)); // Проверка на колличество групп до и после добавления


        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
