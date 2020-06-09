package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() {
    int before = app.getContactHelper().getContactCount(); // колличество контактов до добавления
    if (!app.getContactHelper().isTheAreContact()) {
      app.getContactHelper().createContact(new ContactData("test1", null, "test3", "123","test4", "8880978", "redliane@mail.ru",null), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().acceptNextAlert = true;
    app.getContactHelper().selectDeleteContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount(); // Колличество контактов после добавления
    Assert.assertEquals (after, before - 1); // Проверка на колличество контактов до и после добавления
  }

}
