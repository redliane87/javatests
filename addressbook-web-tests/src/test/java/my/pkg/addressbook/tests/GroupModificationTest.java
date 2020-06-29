package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test001"));
        }
    }

    @Test
    public void testGroupModification() {

        Groups before = app.db().groups(); // колличество групп до добавления. Список
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test001").withHeader("test002").withFooter("test003");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));// Проверка на колличество групп до и после модификации
        Groups after =  app.db().groups(); // Колличество групп после добавления. Список
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }


}
