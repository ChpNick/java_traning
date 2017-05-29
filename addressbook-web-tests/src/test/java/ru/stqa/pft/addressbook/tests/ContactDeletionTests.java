package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();

        if (! app.contact().isThereAContact()) {
            app.contact().create(new ContactData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                    "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                    "-", "-", 1, 2, "1983", 1, 2, "1990", "test1", "-", "-", "-"));
        }
    }

    @Test
    public void testContactDeletionFromHome() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().delete(index);


        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactDeletionFromEdit() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().deleteFromEdit(index);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
