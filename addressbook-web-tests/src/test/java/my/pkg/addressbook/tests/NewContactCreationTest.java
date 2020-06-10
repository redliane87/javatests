package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class NewContactCreationTest extends TestBase {

  @Test
  public void testNewContactCreation(){
    List<ContactData> before = app.getContactHelper().getContactList(); // колличество контактов до добавления
    ContactData contactData = new ContactData("test666", "test2", "test3","123", "test4", "8880978", "redliane@mail.ru", "test001");
    app.getContactHelper().createContact(contactData, true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList(); // Колличество контактов после добавления
    Assert.assertEquals (after.size(), before.size() + 1); // Проверка на колличество контактов до и после добавления

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contactData.setId(max);
    before.add(contactData);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
