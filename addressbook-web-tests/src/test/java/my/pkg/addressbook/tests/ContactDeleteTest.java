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
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("test1", null, "test3", "123", "test4", "8880978", "redliane@mail.ru", null), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDelete() {
        List<ContactData> before = app.contact().list(); // колличество контактов до добавления
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list(); // Колличество контактов после добавления
        Assert.assertEquals(after.size(), before.size() - 1); // Проверка на колличество контактов до и после добавления

        before.remove(index);
        Comparator<? super ContactData> byId = (с1, с2) -> Integer.compare(с1.getId(), с2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);// Сравниваем элементы с одинаковыми индексами
    }



}
