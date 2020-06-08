package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.annotations.*;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation(){

    app.getContactHelper().createContact(new ContactData("test1", null, "test3", "test4", "8880978", "redliane@mail.ru",null), true);


  }


}
