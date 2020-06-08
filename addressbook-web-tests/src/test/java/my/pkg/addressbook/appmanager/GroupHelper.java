package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click (By.name("update"));
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
}
