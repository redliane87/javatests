package my.pkg.addressbook.tests;


import my.pkg.addressbook.model.ContactData;
import org.testng.annotations.Test;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() {
    if (!app.getContactHelper().isTheAreContact()) {
      app.getContactHelper().createContact(new ContactData("test1", null, "test3", "test4", "8880978", "redliane@mail.ru",null), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().acceptNextAlert = true;
    app.getContactHelper().selectDeleteContact();
  }

}
