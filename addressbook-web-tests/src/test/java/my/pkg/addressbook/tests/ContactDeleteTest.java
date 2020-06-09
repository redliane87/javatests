package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class ContactDeleteTest extends TestBase {

    @Test
    public void testContactDelete() {
        if (!app.getContactHelper().isTheAreContact()) {
            app.getContactHelper().createContact(new ContactData("test1", null, "test3", "123", "test4", "8880978", "redliane@mail.ru", null), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().acceptNextAlert = true;
        app.getContactHelper().selectDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size() - 1); // Проверка на колличество контактов до и после добавления

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);// Сравниваем элементы с одинаковыми индексами
    }

}
