package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initModifications();
        app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "8880978", "redliane@mail.ru", null));
        app.getContactHelper().submitContactModification();
    }
}
