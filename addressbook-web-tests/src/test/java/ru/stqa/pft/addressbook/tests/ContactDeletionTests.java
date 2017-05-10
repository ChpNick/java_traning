package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromHome() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().gotoHomePage();

    }

    @Test
    public void testContactDeletionFromEdit() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContactFromEdit();
        app.getNavigationHelper().gotoHomePage();
    }

}
