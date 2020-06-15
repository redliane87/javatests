package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.contact().list(); // колличество контактов до добавления
        int index = before.size() - 1;
        ContactData contactData = new ContactData().withId(before.get(index).getId()).withFName("test1").withLastName("test4").withMidName("test3")
                .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup(null);
        app.contact().modify(index, contactData);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size()); // Проверка на колличество контактов до и после добавления

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
