package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation(){
    int before = app.getContactHelper().getContactCount(); // колличество контактов до добавления
    app.getContactHelper().createContact(new ContactData("test1", null, "test3", "test4", "8880978", "redliane@mail.ru",null), true);
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount(); // Колличество контактов после добавления
    Assert.assertEquals (after, before + 1); // Проверка на колличество контактов до и после добавления

  }


}
