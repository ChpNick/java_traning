package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); // List<GroupDate>.class
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {

//        File photo = new File("src/test/resources/stru.png");
//        contact.withPhotoRelativePath(photo);

        app.goTo().HomePage();

        Contacts before = app.contact().all();

        app.goTo().AddNewContactPage();

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));

//        Проверяем через множества
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());

    }

    @Test
    public void testBadContactCreation() {
        app.goTo().HomePage();

        Contacts before = app.contact().all();

        app.goTo().AddNewContactPage();

        ContactData contact = new ContactData().withFirstname("test1'").withMiddlename("testovich").withLastname("testov")
                .withNickname("Chp").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                .withHomePhone("888888").withMobilePhone("89234567890").withWorkPhone("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(1).withBmons(2)
                .withByear("1983").withAday(1).withAmons(2).withAyear("1990").withGroup("test1").withAddress2("-")
                .withPhone2("-").withNotes("_");

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

//        Проверяем через множества
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
