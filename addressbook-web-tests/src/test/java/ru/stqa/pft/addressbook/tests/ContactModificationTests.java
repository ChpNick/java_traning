package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Nikolay Pechenin on 11.05.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();

        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("test1").withMiddlename("testovich").withLastname("testov")
                    .withNickname("Chp").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                    .withHome("888888").withMobile("89234567890").withWork("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                    .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(1).withBmons(2)
                    .withByear("1983").withAday(1).withAmons(2).withAyear("1990").withGroup("test1").withAddress2("-")
                    .withPhone2("-").withNotes("_"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("test_edit").withMiddlename("testovich_edit").withLastname("testov_edit")
                .withNickname("Chp_edit").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                .withHome("888888").withMobile("89234567890").withWork("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(10).withBmons(10)
                .withByear("1983").withAday(10).withAmons(10).withAyear("1990").withGroup("test1").withAddress2("-")
                .withPhone2("-").withNotes("_");

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
