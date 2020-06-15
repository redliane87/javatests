package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class NewContactCreationTest extends TestBase {

    @Test
    public void testNewContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list(); // колличество контактов до добавления
        ContactData contactData = new ContactData("test668", "test4", "test3", "123", "test4", "8880978", "redliane@mail.ru", "test001");
        app.contact().create(contactData, true);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size() + 1); // Проверка на колличество контактов до и после добавления


        contactData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contactData);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
