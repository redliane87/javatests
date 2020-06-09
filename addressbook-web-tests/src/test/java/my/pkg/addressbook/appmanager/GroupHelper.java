package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCretion() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getGname());
        type(By.name("group_header"), groupData.getGheader());
        type(By.name("group_footer"), groupData.getGfooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void selectDeleteGroup() {
        click(By.xpath("//input[5]"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void creatGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCretion();
        returnToGroupPage();
    }

    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String gname = element.getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            GroupData group = new GroupData(id, gname, null, null);
            groups.add(group);
        }
        return groups;
    }
}
