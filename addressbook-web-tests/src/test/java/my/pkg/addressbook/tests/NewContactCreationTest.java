package my.pkg.addressbook.tests;

import com.google.inject.internal.util.SourceProvider;
import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import my.pkg.addressbook.model.GroupData;
import org.testng.annotations.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/mol.jpg");
        BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFName(split[0]).withLastName(split[1]).withMidName(split[2]).withNickName(split[3])
                    .withMobPhone(split[4]).withHomePhone(split[5]).withWorkPhone(split[6])
                    .withEmail(split[7]).withEmail2(split[8]).withEmail3(split[9])
                    .withAddress(split[10]).withPhoto(photo).withGroup("test001")});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testNewContactCreation(ContactData contactData) {
        app.goTo().homePage();
        Contacts before = app.contact().all(); // колличество контактов до добавления
        app.contact().create(contactData);
        app.goTo().homePage();
        Contacts after = app.contact().all(); // Колличество контактов после добавления
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1)); // Проверка на колличество контактов до и после добавления

        assertThat(after, equalTo(before.
                withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
