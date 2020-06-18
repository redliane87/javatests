package my.pkg.addressbook.tests;

import my.pkg.addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactAddressTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFName("test1").withLastName("test4").withMidName("test3")
                    .withNickName("123").withMobPhone("8880978").withEmail("redliane@mail.ru").withAddress("testAddress"), true);
            app.goTo().homePage();
        }
    }
    @Test
    public void testContactAddress(){
        app.goTo().homePage();
        ContactData contactData = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contactData);

        MatcherAssert.assertThat(contactData.getAddress(), CoreMatchers.equalTo(contactInfoFormEditForm.getAddress()));
}}
