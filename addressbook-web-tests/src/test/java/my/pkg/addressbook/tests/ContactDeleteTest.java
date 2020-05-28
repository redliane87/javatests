package my.pkg.addressbook.tests;


import org.testng.annotations.Test;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() {
    app.gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().acceptNextAlert = true;
    app.getContactHelper().selectDeleteContact();
  }

}
