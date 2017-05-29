package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Nikolay Pechenin on 11.05.2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.goTo().HomePage();

        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                    "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                    "-", "-", 1, 2, "1983", 1, 2, "1990", "test1", "-", "-", "-"));
        }

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        ContactData contact = new ContactData(before.get(index).getId(), "test_edit", "testovich_edit", "testov_edit", "Chp_edit", "i like coding", "social quantum",
                "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                "-", "-", 10, 10, "1983", 10, 10, "1990", null, "-", "-", "-");

        app.contact().modify(index, contact);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());


//        Проверяем через множества
        before.remove(index);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

//        Проверяем по отсортированным спискам
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
