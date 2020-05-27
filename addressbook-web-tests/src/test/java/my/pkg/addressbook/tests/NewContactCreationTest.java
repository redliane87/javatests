package my.pkg.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    initContact();
    fillContactForm(new ContactData("test1", "test2", "test3", "test4", "8880978", "redliane@mail.ru"));
    sobmitContactCreation();

  }


}
