package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.annotations.*;

public class NewAddressBookCreationTests extends TestBase{

  @Test
  public void testNewAddressBookCreation() throws Exception {
    app.getContactHelper().initAdressCreation("add new");
    app.getContactHelper().fillAddressForm(new ContactData("Test", "Oleg", "Test", "test11", "Олеко Дундича улица, 7", "redliane87@mail.ru", "redliane87@mail.ru", "redliane87@mail.ru", "15", "October", "1987"));
  }
}
