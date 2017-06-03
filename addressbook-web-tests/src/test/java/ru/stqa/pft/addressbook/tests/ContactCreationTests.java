package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();

        Contacts before = app.contact().all();

        app.goTo().AddNewContactPage();

        ContactData contact = new ContactData().withFirstname("test1").withMiddlename("testovich").withLastname("testov")
                .withNickname("Chp").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                .withHomePhone("888888").withMobilePhone("89234567890").withWorkPhone("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(1).withBmons(2)
                .withByear("1983").withAday(1).withAmons(2).withAyear("1990").withGroup("test1").withAddress2("-")
                .withPhone2("-").withNotes("_");

        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));

//        Проверяем через множества
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
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
