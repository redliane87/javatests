package my.pkg.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactDeleteTest {
  private WebDriver wd;
  private boolean acceptNextAlert = true;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://127.0.0.1/addressbook/");
    login("admin", "secret");
  }

  public void login(String user, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(user);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactDelete() throws Exception {

    selectContact();
    acceptNextAlert = true;
    selectDeleteContact();
  }

  public void selectDeleteContact() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void selectContact() {
    wd.findElement(By.xpath("//tr[8]/td/input")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    gotoHomePage();
    logout();
    wd.quit();
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
