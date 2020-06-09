package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {

        if (! app.getContactHelper().isTheAreContact()) {
            app.getContactHelper().createContact(new ContactData("test1", null, "test3","123", "test4", "8880978", "redliane@mail.ru",null), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initModifications();
        app.getContactHelper().fillContactForm(new ContactData("test999", "test2", "test3","123", "test4", "8880978", "redliane@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
        Assert.assertEquals (after.size(), before.size()); // Проверка на колличество контактов до и после добавления
    }
}
