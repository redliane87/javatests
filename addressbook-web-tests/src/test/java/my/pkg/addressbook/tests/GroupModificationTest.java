package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification () {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount(); // колличество групп до добавления
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creatGroup(new GroupData("test001", null, null));
        }
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test004", "test002", "test003"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount(); // Колличество групп после добавления
        Assert.assertEquals (after, before); // Проверка на колличество групп до и после добавления
    }
}
