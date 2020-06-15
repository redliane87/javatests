package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getContactHelper().isTheAreContact()) {
            app.getContactHelper().createContact(new ContactData("test1", null, "test3", "123", "test4", "8880978", "redliane@mail.ru", null), true);
            app.getNavigationHelper().gotoHomePage();
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
        int index = before.size() - 1;
        ContactData contactData = new ContactData(before.get(index).getId(), "test999", "test2", "test3", "123", "test4", "8880978", "redliane@mail.ru", null);
        app.getContactHelper().modifyContact(index, contactData);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size()); // Проверка на колличество контактов до и после добавления

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
