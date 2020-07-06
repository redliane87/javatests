package my.pkg.addressbook.tests;

import my.pkg.addressbook.TestBase;
import my.pkg.addressbook.model.ContactData;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class AddContactToGroupTest extends TestBase {
    /**
     * .Берем все контакты -> фильтруем их на наличие группы (оставляя только те, у которых нет групп) ->
     * в отфильтрованной коллекции берем итератором свободный от группы контакт и добавляем его группу
     * обновляем данные по нашему контакту
     **/
    @BeforeMethod
    public void ensurePreconditions() {


        if (app.db().contacts().stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()).size() == 0) {
            app.contact().create(new ContactData()
                    .withFName("test3").withLastName("test4").withMidName("test3").withNickName("123")
                    .withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress"));
            app.goTo().homePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData contact = app.db().contacts().
                stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()).
                iterator().next();
        app.contact().addToGroup(contact, group);
        int contactId = contact.getId();
        contact = app.db().contacts().
                stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet()).
                iterator().next();

        assertThat(contact.getGroups(), contains(group));
    }
}


   /* Зашел в тупик с подходом, вернуться в свободное время (проанализировать проверку контакта на наличие группы, если группа есть создать новый контакт)
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

    }*/

