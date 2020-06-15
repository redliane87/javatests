package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
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
        Contacts before = app.contact().all(); // колличество контактов до добавления
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifiedContact.getId()).withFName("test105").withLastName("test555").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup(null);
        app.contact().modify(contactData);
        app.goTo().homePage();
        Contacts after = app.contact().all(); // Колличество контактов после добавления
        assertEquals(after.size(), before.size()); // Проверка на колличество контактов до и после добавления
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

    }


}
