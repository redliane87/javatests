package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testNewContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all(); // колличество контактов до добавления
        ContactData contactData
                = new ContactData().withFName("test668").withLastName("test4").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup("test001");
        app.contact().create(contactData, true);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size() + 1); // Проверка на колличество контактов до и после добавления



        contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contactData);
        Assert.assertEquals(before, after);
    }


}
