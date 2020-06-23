package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String file;
    @Parameter (names = "-d", description = "Data format")
    public String format;
    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }


    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contactData : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contactData.getfName(), contactData.getLastName(), contactData.getMidName(), contactData.getNickName(),
                    contactData.getMobPhone(), contactData.getHomePhone(), contactData.getWorkPhone(), contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3(), contactData.getAddress(), contactData.getGroup()));
        }
        writer.close();
    }
    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFName(String.format("test %s", i))
                    .withLastName(String.format("lastName %s", i)).withMidName(String.format("midName %s", i)).withNickName(String.format("nickName %s", i))
                    .withMobPhone(String.format("mobPhone %s", i)).withHomePhone(String.format("homePhone %s", i)).withWorkPhone(String.format("workPhone %s", i))
                    .withEmail(String.format("red@mail.ru %s", i)).withEmail2(String.format("red2@mail.ru %s", i)).withEmail3(String.format("red3@mail.ru %s", i))
                    .withAddress(String.format("address %s", i)).withGroup(String.format("test001 %s", i)));
        }
        return contacts;
    }
}
