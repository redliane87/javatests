package my.pkg.addressbook.tests;

import org.testng.annotations.*;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    app.initContact();
    app.fillContactForm(new ContactData("test1", "test2", "test3", "test4", "8880978", "redliane@mail.ru"));
    app.sobmitContactCreation();

  }


}
