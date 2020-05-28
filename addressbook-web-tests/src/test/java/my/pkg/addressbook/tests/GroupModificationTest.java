package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.*;


public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification () {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test004", "test002", "test003"));
        app.getGroupHelper().submitGroupModification();
    }
}
