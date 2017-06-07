package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nikolay Pechenin on 07.06.2017.
 */
public class ContactDeleteInGroupTest extends TestBase {

    protected Groups beforeGroups;
    protected Groups afterGroups;

    protected Contacts beforeContacts;
    protected Contacts afterContacts;

    protected GroupData group;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstname("test1").withLastname("testov")
                    .withAddress("Ivanovo").withHomePhone("888888").withMobilePhone("89234567890")
                    .withWorkPhone("12345678900").withEmail("chpnick@mail.ru")
                    .withEmail2("chpnick1@gmail.ru").withEmail3("-"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactDeleteInGroup() {
        beforeContacts = app.db().contacts(); // список контактов до
        ContactData contact = beforeContacts.iterator().next(); // выбранный случайный контакт

        beforeGroups = app.db().groups(); // список групп до

        if (contact.getGroups().size() == 0) {
            System.out.println("У контакта пустой список групп");
            group = beforeGroups.iterator().next(); // выбранная случайная группа
        } else {
            System.out.println("У контакта есть группы");
            group = contact.getGroups().iterator().next(); // выбранная случайная группа
        }

        app.contact().deleteContactInGroup(contact, group);

        contact.withoutGroup(group);
        group.withoutContact(contact);

        afterContacts = app.db().contacts();
        afterGroups = app.db().groups();

        assertThat(afterGroups, equalTo(beforeGroups));
        assertThat(afterContacts, equalTo(beforeContacts));

        for (ContactData contactDb : afterContacts) {
            if (contactDb.getId() == contact.getId()) {
                assertThat(contactDb.getGroups(), equalTo(contact.getGroups()));
                break;
            }
        }

        verifyContactListInUi();
    }
}
