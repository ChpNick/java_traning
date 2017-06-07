package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nikolay Pechenin on 07.06.2017.
 */
public class ContactAddInGroupTest extends TestBase {
    protected Groups beforeGroups;
    protected Groups afterGroups;

    protected Contacts beforeContacts;
    protected Contacts afterContacts;

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
    public void testAddContactInGroup() {
        beforeContacts = app.db().contacts(); // список контактов до
        ContactData contact = beforeContacts.iterator().next(); // выбранный случайный контакт

        beforeGroups = app.db().groups(); // список групп до

        if (contact.getGroups().equals(beforeGroups)) {
            System.out.println("Контакт состоит во всех группа, создаем новую");

            GroupData newGroup = new GroupData().withName("newGroup");
            app.goTo().GroupPage();
            app.group().create(newGroup);

            Groups newGroupsDb = app.db().groups();  // список групп с новой группой
            newGroupsDb.removeAll(beforeGroups); // удаляем старые группы и остается только новая
            GroupData ourGroup = newGroupsDb.iterator().next(); // это наша новая группа из базы

            newGroup.withId(ourGroup.getId());

            app.contact().AddContactInGroup(contact, newGroup);
            app.goTo().HomePage();

            newGroup.inContact(contact);
            contact.inGroup(newGroup);

            afterContacts = app.db().contacts();
            afterGroups = app.db().groups();

            assertThat(afterGroups, equalTo(beforeGroups.withAdded(newGroup)));
            assertThat(afterContacts, equalTo(beforeContacts));

            for (ContactData contactDb : afterContacts) {
                if (contactDb.getId() == contact.getId()) {
                    assertThat(contactDb.getGroups(), equalTo(contact.getGroups()));
                    break;
                }
            }

            verifyContactListInUi();

        } else {
            System.out.println("Контакт не во всех группах, добавляем одну из недостающих");

            Groups contactGroups = contact.getGroups();
            Groups newGroups = new Groups(beforeGroups);
            newGroups.removeAll(contactGroups);
            GroupData newGroup = newGroups.iterator().next();

            app.contact().AddContactInGroup(contact, newGroup);
            app.goTo().HomePage();

            newGroup.inContact(contact);
            contact.inGroup(newGroup);

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
}
