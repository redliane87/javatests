package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.GroupData;
import my.pkg.addressbook.model.Groups;
import org.testng.annotations.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
           String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
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
