package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFName("test1").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup(null), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all(); // колличество контактов до добавления
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifiedContact.getId()).withFName("test105").withLastName("test555").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup(null);
        app.contact().modify(contactData);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size()); // Проверка на колличество контактов до и после добавления

        before.remove(modifiedContact);
        before.add(contactData);
        Assert.assertEquals(before, after);
    }


}
