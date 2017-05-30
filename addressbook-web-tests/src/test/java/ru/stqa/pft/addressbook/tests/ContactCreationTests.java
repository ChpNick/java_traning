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
        app.goTo().HomePage();

        List<ContactData> before = app.contact().list();

        app.goTo().AddNewContactPage();

        ContactData contact = new ContactData().withFirstname("test1").withMiddlename("testovich").withLastname("testov")
                .withNickname("Chp").withTitle("i like coding").withCompany("social quantum").withAddress("Ivanovo")
                .withHome("888888").withMobile("89234567890").withWork("12345678900").withFax("-").withEmail("chpnick@mail.ru")
                .withEmail2("chpnick1@gmail.ru").withEmail3("-").withHomepage("-").withBday(1).withBmons(2)
                .withByear("1983").withAday(1).withAmons(2).withAyear("1990").withGroup("test1").withAddress2("-")
                .withPhone2("-").withNotes("_");

        app.contact().create(contact);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

//        Проверяем через множества
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        contact.withId(after.stream().max(byId).get().getId());
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

//        Проверяем по отсортированным спискам
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
