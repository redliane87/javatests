package my.pkg.addressbook;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class DeleteAdressTest extends TestBase{

  @Test
  public void testDeleteAdress() throws Exception {
    selectAdress(By.id("4"));
    acceptNextAlert = true;
    deleteAdress(By.xpath("//input[@value='Delete']"));
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }


}
