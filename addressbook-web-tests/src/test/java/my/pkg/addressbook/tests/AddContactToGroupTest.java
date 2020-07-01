package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test001")
                        .withHeader("test002").withFooter("test003"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData()
                    .withFName("test3").withLastName("test4").withMidName("test3").withNickName("123")
                            .withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").inGroup(groups.iterator().next()));
            app.goTo().homePage();
        }
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() == groups.size()) {
                app.contact().create(new ContactData()
                        .withFName("test3").withLastName("test4").withMidName("test3").withNickName("123")
                        .withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").inGroup(groups.iterator().next()));
            }
            return;
        }
        app.goTo().homePage();
        }

    @Test
    public void testAddContactToGroup () {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() != groups.size()) {
                Groups groupsBefore = contactData.getGroups();
                app.contact().addToGroup(contactData, group);
                Contacts updateContacts = app.db().contacts();
                for (ContactData updateContact : updateContacts) {
                    if (updateContact.getId() == contactData.getId()) {
                        Groups groupAfter = updateContact.getGroups();
                        assertThat(groupAfter.size(), equalTo(groupsBefore.size() + 1));
                        Groups groupsWithAdded = new Groups(groupAfter);
                        groupsWithAdded.removeAll(groupsBefore);
                        assertThat(groupAfter, equalTo(groupsBefore.withAdded(groupsWithAdded.iterator().next())));
                    }
                }
                app.goTo().homePage();
                app.contact().showAllGroups();
            }
        }
        /*
        @Test
        public void testAddContactToGroup () {
            Contacts before = app.db().contacts(); // колличество контактов до добавления
            ContactData findAccount = before.iterator().next();
            ContactData contactData = new ContactData().withFName("test3").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress");
            app.contact().addToGroup(findAccount);
            app.goTo().homePage();
        } */
    }
}
