package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation(){
    List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
    app.getContactHelper().createContact(new ContactData("test1", null, "test3","123","test4", "8880978", "redliane@mail.ru","test001"), true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
    Assert.assertEquals (after.size(), before.size() + 1); // Проверка на колличество контактов до и после добавления

  }


}
