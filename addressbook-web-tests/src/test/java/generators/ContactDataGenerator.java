package generators;

import my.pkg.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contactData : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contactData.getfName(), contactData.getLastName(), contactData.getMidName(), contactData.getNickName(),
                    contactData.getMobPhone(), contactData.getHomePhone(), contactData.getWorkPhone(), contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3(), contactData.getAddress()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFName(String.format("test %s", i))
                    .withLastName(String.format("lastName %s", i)).withMidName(String.format("midName %s", i)).withNickName(String.format("nickName %s", i))
                    .withMobPhone(String.format("mobPhone %s", i)).withHomePhone(String.format("homePhone %s", i)).withWorkPhone(String.format("workPhone %s", i))
                    .withEmail(String.format("red@mail.ru %s", i)).withEmail2(String.format("red2@mail.ru %s", i)).withEmail3(String.format("red3@mail.ru %s", i))
                    .withAddress(String.format("address %s", i)));
        }
        return contacts;
    }
}
