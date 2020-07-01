package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test001")
                        .withHeader("test002").withFooter("test003"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData().withFName("test3").withLastName("test4").withMidName("test3").withNickName("123")
                    .withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress"));
        }
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() != groups.size()) {
                app.contact().addToGroup(contactData);
            }
        }
        app.goTo().homePage();
    }
    @Test
    public void testRemoveContactFromGroup () {
        Contacts accounts = app.db().contacts();
        for (ContactData account : accounts) {
            if (account.getGroups().size() != 0) {
                Groups groupsBefore = account.getGroups();
                app.contact().removedFromGroup(account);
                Contacts updatedAccounts = app.db().contacts();
                for (ContactData updatedAccount : updatedAccounts) {
                    if (updatedAccount.getId() == account.getId()) {
                        Groups groupsAfter = updatedAccount.getGroups();
                        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
                        groupsBefore.removeAll(groupsAfter);
                        assertThat(groupsAfter, equalTo(account.getGroups().without(groupsBefore.iterator().next())));
                    }
                }
                app.goTo().homePage();
                app.contact().showAllGroups();
            }
        }

    }
}
