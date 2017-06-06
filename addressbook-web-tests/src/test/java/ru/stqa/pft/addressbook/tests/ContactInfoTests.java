package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nikolay Pechenin on 03.06.2017.
 */
public class ContactInfoTests extends TestBase {

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
    public void testContactPhone() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test
    public void testContactEmail() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    @Test
    public void testContactAddress() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()
                .replaceAll("[\\s]{2,}", " ").trim()));
    }

    @Test
    public void testContactDetails() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().withFirstname("CrazyChp").withLastname("testov")
                .withAddress("  Ivanovo   city \n big  ").withHomePhone("8 (920) 1111").withWorkPhone("33 33-33")
                .withEmail("chpnick@mail.ru").withEmail3("chpnick1@gmail.ru");

        app.contact().create(newContact);

        Contacts after = app.contact().all();
        after.removeAll(before);
        ContactData contact = after.iterator().next();

        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String contactInfoFromEdit = mergeInfoFromEditForDetails(contactInfoFromEditForm);

        String contactInfoFromDetails = app.contact().infoFromDetailsForm(contact);

        assertThat(contactInfoFromEdit, equalTo(contactInfoFromDetails));
    }

    public String mergeInfoFromEditForDetails(ContactData contact) {
        String email = mergeEmails(contact);

        String contactInfo = String.format("%s %s %s ", contact.getFirstname(),
                contact.getLastname(), contact.getAddress());

        if (! contact.getHomePhone().equals("")) {
            contactInfo += "H: " + contact.getHomePhone() + " ";
        }

        if (! contact.getMobilePhone().equals("")) {
            contactInfo += "M: " + contact.getMobilePhone() + " ";
        }

        if (! contact.getWorkPhone().equals("")) {
            contactInfo += "W: " +contact.getWorkPhone() + " ";
        }

        contactInfo += email;
//        System.out.println(contactInfo.replaceAll("\n", " ").replaceAll("[\\s]{2,}", " ").trim());
        return contactInfo.replaceAll("\n", " ").replaceAll("[\\s]{2,}", " ").trim();
    }

}
