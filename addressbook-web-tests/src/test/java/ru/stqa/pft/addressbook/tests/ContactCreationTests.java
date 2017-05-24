package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoAddNewContactPage();

        ContactData contact = new ContactData("test1", "testovich", "testov", "Chp", "i like coding", "social quantum",
                "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                "-", "-", 1, 2, "1983", 1, 2, "1990", "test1" ,"-", "-", "-");

        app.getContactHelper().createContact(contact);

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

//        Проверяем через множества
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        contact.setId(after.stream().max(byId).get().getId());
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

//        Проверяем по отсортированным спискам
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
