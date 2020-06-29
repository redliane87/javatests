package my.pkg.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroupCreationTest extends TestBase {



    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
       try (BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/groups.xml")))){
           String xml = "";
           String line = reader.readLine();
           while (line != null){
               xml += line;
               line = reader.readLine();
           }
           XStream xStream = new XStream();
           xStream.processAnnotations(GroupData.class);
           List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
           return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/groups.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); // List<GroupData>.class
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validGroupsFromJson")
    public void testNewGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups(); // колличество групп до добавления. Список
        app.group().create(group);
        Groups after = app.db().groups(); // Колличество групп после добавления. Список
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1)); // Проверка на колличество групп до и после добавления

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
