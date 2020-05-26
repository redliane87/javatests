package my.pkg.addressbook.tests;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class DeleteAdressTest extends TestBase{

  @Test
  public void testDeleteAdress() throws Exception {
    app.getContactHelper().selectAdress(By.id("12"));
    app.getAlertHelper().acceptNextAlert = true;
    app.getContactHelper().deleteAdress(By.xpath("//input[@value='Delete']"));
    assertTrue(app.getAlertHelper().closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }


}
