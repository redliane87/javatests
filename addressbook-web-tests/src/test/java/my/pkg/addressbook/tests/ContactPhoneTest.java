package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            ContactData contactData = new ContactData().withFName("test1").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress").withGroup("test003");
            app.contact().create(contactData);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contactData = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contactData);

        assertThat(contactData.getAllphones(), equalTo(mergePhones(contactInfoFormEditForm)));
    }

    private String mergePhones(ContactData contactData) {
        return Arrays.asList(contactData.getHomePhone(), contactData.getMobPhone(), contactData.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
