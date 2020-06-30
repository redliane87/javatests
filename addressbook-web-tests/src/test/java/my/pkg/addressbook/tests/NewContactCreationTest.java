package my.pkg.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactCreationTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContactsXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
       // File photo = new File("src/test/resources/mol.jpg");
        try (BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
       try (BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/contacts.json")))){
           String json = "";
           String line = reader.readLine();
           while (line != null){
               json += line;
               line = reader.readLine();
           }
           Gson gson = new Gson();
           List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); // List<ContactData>.class
           return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }
    }

    @Test (dataProvider = "validContactsJson")
    public void testNewContactCreation(ContactData contactData) {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test001"));
            app.goTo().homePage();
        }
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts before = app.db().contacts(); // колличество контактов до добавления
        app.contact().create(contactData.inGroup(groups.iterator().next()));
        app.goTo().homePage();
        Contacts after = app.db().contacts(); // Колличество контактов после добавления
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1)); // Проверка на колличество контактов до и после добавления
        assertThat(after, equalTo(before.
                withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();

    }

}
