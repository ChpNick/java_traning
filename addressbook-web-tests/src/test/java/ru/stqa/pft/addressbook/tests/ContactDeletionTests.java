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
        if (app.db().contacts().size() == 0 ) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstname("test1").withLastname("testov")
                    .withAddress("Ivanovo").withHomePhone("888888").withMobilePhone("89234567890")
                    .withWorkPhone("12345678900").withEmail("chpnick@mail.ru")
                    .withEmail2("chpnick1@gmail.ru").withEmail3("-"));
        }
    }

    @Test
    public void testContactDeletionFromHome() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUi();
    }

    @Test
    public void testContactDeletionFromEdit() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().deleteFromEdit(deletedContact);

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUi();
    }
}
