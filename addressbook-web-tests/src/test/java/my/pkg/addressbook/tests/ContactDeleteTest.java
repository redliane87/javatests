package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFName("test1").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup(null), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDelete() {
        Contacts before = app.contact().all(); // колличество контактов до добавления
        ContactData delContact = before.iterator().next();
        app.contact().delete(delContact);
        app.goTo().homePage();
        Contacts after = app.contact().all(); // Колличество контактов после добавления
        assertEquals(after.size(), before.size() - 1); // Проверка на колличество контактов до и после добавления
        assertThat(after, equalTo(before.without(delContact)));


    }


}
