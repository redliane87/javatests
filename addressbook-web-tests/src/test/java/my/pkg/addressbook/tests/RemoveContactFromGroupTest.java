package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

public class RemoveContactFromGroupTest extends TestBase {
    /**
     * .Берем все контакты -> фильтруем их на наличие группы (оставляя только те, у которых нет групп) ->
     * в отфильтрованной коллекции берем итератором свободный от группы контакт и добавляем его группу
     * обновляем данные по нашему контакту
     **/
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFName("test3").withLastName("test4").withMidName("test3").withNickName("123")
                    .withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress"));
            app.goTo().homePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
            app.goTo().homePage();
        }

        if (app.db().groups().stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).isEmpty()) {
            GroupData groupTemp = app.db().groups().iterator().next();
            ContactData contactTemp = app.db().contacts().iterator().next();
            app.contact().addToGroup(contactTemp, groupTemp);
        }
    }

    @Test
    public void testContactFromGroup() {
        app.goTo().homePage();
        GroupData group = app.db().groups().
                stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).
                iterator().next();
        ContactData contact = app.db().contacts().
                stream().filter(c -> c.getGroups().contains(group)).collect(toSet()).
                iterator().next();
        app.contact().removedFromGroup(contact, group);

        int contactId = contact.getId();
        contact = app.db().contacts().
                stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet()).
                iterator().next();
        assertThat(contact.getGroups(), not(contains(group)));
    }
}
/* Зашел в тупик с подходом, вернуться в свободное время (тест валится когда в Контактах несколько контактов с группами а так же нет не одного контакта))
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
        GroupData group = groups.iterator().next();
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() != groups.size()) {
                app.contact().addToGroup(contactData, group);
            }
        }
        app.goTo().homePage();
    }
    @Test
    public void testRemoveContactFromGroup () {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() != 0) {
                Groups groupsBefore = contactData.getGroups();
                app.contact().removedFromGroup(contactData, group);
                Contacts updatedAccounts = app.db().contacts();
                for (ContactData updatedAccount : updatedAccounts) {
                    if (updatedAccount.getId() == contactData.getId()) {
                        Groups groupsAfter = updatedAccount.getGroups();
                        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
                        groupsBefore.removeAll(groupsAfter);
                        assertThat(groupsAfter, equalTo(contactData.getGroups().without(groupsBefore.iterator().next())));
                    }
                }
                app.goTo().homePage();
                app.contact().showAllGroups();
            }
        }

    }
*/
