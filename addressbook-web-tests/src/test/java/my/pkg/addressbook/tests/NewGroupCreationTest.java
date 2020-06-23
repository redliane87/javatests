package my.pkg.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/groups.xml")));
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

    @Test (dataProvider = "validGroups")
    public void testNewGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all(); // колличество групп до добавления. Список
        app.group().create(group);
        Groups after = app.group().all(); // Колличество групп после добавления. Список
        assertThat(app.group().getGroupCount(), equalTo(before.size() + 1)); // Проверка на колличество групп до и после добавления

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
