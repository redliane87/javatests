package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteTest extends TestBase {
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
    public void testContactDelete() {
        Contacts before = app.db().contacts(); // колличество контактов до добавления
        ContactData delContact = before.iterator().next();
        app.contact().delete(delContact);
        app.goTo().homePage();
        Contacts after = app.db().contacts(); // Колличество контактов после добавления
        assertThat(app.contact().getContactCount(), equalTo(before.size() - 1)); // Проверка на колличество контактов до и после добавления
        assertThat(after, equalTo(before.without(delContact)));
        verifyContactListInUI();


    }


}
