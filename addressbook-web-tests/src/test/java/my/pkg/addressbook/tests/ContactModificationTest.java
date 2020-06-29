package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import org.testng.annotations.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            ContactData contactData = new ContactData().withFName("test3").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup("test003");
            app.contact().create(contactData);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts(); // колличество контактов до добавления
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifiedContact.getId()).withFName("test105").withLastName("test555").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup("test001");
        app.contact().modify(contactData);
        app.goTo().homePage();
        Contacts after = app.db().contacts(); // Колличество контактов после добавления
        assertThat(app.contact().getContactCount(), equalTo(before.size())); // Проверка на колличество контактов до и после модификации
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

    }


}
