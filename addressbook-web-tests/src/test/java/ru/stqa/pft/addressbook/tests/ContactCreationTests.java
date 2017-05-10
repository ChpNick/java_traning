package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoAddNewContactPage();
        app.getContactHelper().fillContactForm(new AddressData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                "-", "-", 1, 2, "1983", 1, 2, "1990", "-", "-", "-"));
        app.getContactHelper().submitNewContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
