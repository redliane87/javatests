package my.pkg.addressbook.tests;


import org.testng.annotations.*;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws Exception {

    app.gotoHomePage();
    app.selectContact();
    app.acceptNextAlert = true;
    app.selectDeleteContact();
  }

}
