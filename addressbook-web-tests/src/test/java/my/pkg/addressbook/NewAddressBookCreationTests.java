package my.pkg.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NewAddressBookCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.id("LoginForm")).submit();
  }

  @Test
  public void testNewAddressBookCreation() throws Exception {
    initAdressCreation("add new");
    fillAddressForm(new GroupData("Test", "Oleg", "Test", "test11", "Олеко Дундича улица, 7", "redliane87@mail.ru", "redliane87@mail.ru", "redliane87@mail.ru", "15", "October", "1987"));
  }

  private void fillAddressForm(GroupData groupData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(groupData.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(groupData.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(groupData.getLastname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(groupData.getNickname());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(groupData.getAddress());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(groupData.getEmail());
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys(groupData.getEmail2());
    wd.findElement(By.name("email3")).clear();
    wd.findElement(By.name("email3")).sendKeys(groupData.getEmail3());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(groupData.getBday());
    wd.findElement(By.name("bday")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(groupData.getBmonth());
    wd.findElement(By.name("bmonth")).click();
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(groupData.getByear());
    wd.findElement(By.xpath("//input[21]")).click();
  }

  private void initAdressCreation(String s) {
    wd.findElement(By.linkText(s)).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    gotoHomePage("home");
    logout("Logout");
    wd.quit();

  }

  private void logout(String logout) {
    wd.findElement(By.linkText(logout)).click();
  }

  private void gotoHomePage(String home) {
    wd.findElement(By.linkText(home)).click();
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


}
