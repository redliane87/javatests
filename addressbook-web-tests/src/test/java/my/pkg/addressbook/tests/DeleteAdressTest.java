package my.pkg.addressbook.tests;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class DeleteAdressTest extends TestBase{

  @Test
  public void testDeleteAdress() throws Exception {
    app.selectAdress(By.id("3"));
    app.acceptNextAlert = true;
    app.deleteAdress(By.xpath("//input[@value='Delete']"));
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }


}
