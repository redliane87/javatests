package my.pkg.addressbook.tests;


import org.testng.annotations.*;


public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDelete() throws Exception {

    gotoHomePage();
    selectContact();
    acceptNextAlert = true;
    selectDeleteContact();
  }

}
