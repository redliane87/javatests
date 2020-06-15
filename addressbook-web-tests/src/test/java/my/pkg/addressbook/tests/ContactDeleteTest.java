package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;


public class ContactDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getContactHelper().isTheAreContact()) {
            app.getContactHelper().createContact(new ContactData("test1", null, "test3", "123", "test4", "8880978", "redliane@mail.ru", null), true);
            app.getNavigationHelper().gotoHomePage();
        }
    }

    @Test
    public void testContactDelete() {
        List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().acceptNextAlert = true;
        app.getContactHelper().selectDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size() - 1); // Проверка на колличество контактов до и после добавления

        before.remove(before.size() - 1);
        Comparator<? super ContactData> byId = (с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);// Сравниваем элементы с одинаковыми индексами
    }

}
