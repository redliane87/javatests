package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        int before = app.getContactHelper().getContactCount(); // колличество контактов до добавления
        if (! app.getContactHelper().isTheAreContact()) {
            app.getContactHelper().createContact(new ContactData("test1", null, "test3","123", "test4", "8880978", "redliane@mail.ru",null), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().initModifications();
        app.getContactHelper().fillContactForm(new ContactData("test999", "test2", "test3","123", "test4", "8880978", "redliane@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount(); // Колличество контактов после добавления
        Assert.assertEquals (after, before); // Проверка на колличество контактов до и после добавления
    }
}
