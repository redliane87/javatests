package my.pkg.addressbook.tests;

import com.google.inject.internal.util.SourceProvider;
import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import org.testng.annotations.*;


import java.io.File;
import java.sql.SQLOutput;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactCreationTest extends TestBase {

    @Test
    public void testNewContactCreation() {
        app.goTo().homePage();
        File photo = new File("src/test/resources/mol.jpg");
        Contacts before = app.contact().all(); // колличество контактов до добавления
        ContactData contactData
                = new ContactData().withFName("test668").withLastName("test4").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup("test001").withPhoto(photo);
        app.contact().create(contactData);

        app.goTo().homePage();
        Contacts after = app.contact().all(); // Колличество контактов после добавления
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1)); // Проверка на колличество контактов до и после добавления

        assertThat(after, equalTo(before.
                withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
