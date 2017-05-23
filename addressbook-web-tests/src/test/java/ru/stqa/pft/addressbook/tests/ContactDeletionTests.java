package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromHome() {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                    "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                    "-", "-", 1, 2, "1983", 1, 2, "1990", "test1", "-", "-", "-"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();


        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);


    }

    @Test
    public void testContactDeletionFromEdit() {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                    "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                    "-", "-", 1, 2, "1983", 1, 2, "1990", "test1", "-", "-", "-"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().deleteContactFromEdit();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }

}
