package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Nikolay Pechenin on 11.05.2017.
 */
public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("test_edit")
                .withLastname(modifiedContact.getLastname()).withAddress("Ivanovo").withHomePhone("888888")
                .withMobilePhone("89234567890").withWorkPhone("12345678900").withEmail("chpnick@mail.ru")
                .withEmail2("chpnick1@gmail.ru").withEmail3("-");

        app.contact().modify(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

//        Проверяем через множества
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
