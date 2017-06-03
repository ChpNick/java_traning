package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();

        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("test1").withMiddlename("testovich").withLastname("testov")
                    .withNickname("Chp").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                    .withHome("888888").withMobile("89234567890").withWork("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                    .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(1).withBmons(2)
                    .withByear("1983").withAday(1).withAmons(2).withAyear("1990").withGroup("test1").withAddress2("-")
                    .withPhone2("-").withNotes("_"));
        }
    }

    @Test
    public void testContactDeletionFromHome() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test
    public void testContactDeletionFromEdit() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().deleteFromEdit(deletedContact);

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
