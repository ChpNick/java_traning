package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactHelper extends HelperBase {
    private final NavigationHelper navigationHelper;
    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
        navigationHelper = new NavigationHelper(wd);
    }

    public void submitNewContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());

        attach(By.name("photo"), contactData.getPhotoRelativePath());

        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());

        if (contactData.getBday() != 0) {
            select(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.getBday() + 2) + "]"));
        }

        if (contactData.getBmons() != 0) {
            select(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.getBmons() + 2) + "]"));
        }

        type(By.name("byear"), contactData.getByear());

        if (contactData.getAday() != 0) {
            select(By.xpath("//div[@id='content']/form/select[3]//option[" + (contactData.getAday() + 2) + "]"));
        }

        if (contactData.getAmons() != 0) {
            select(By.xpath("//div[@id='content']/form/select[4]//option[" + (contactData.getAmons() + 2) + "]"));
        }

        type(By.name("ayear"), contactData.getAyear());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
    }

    //    Выбирает группу в которую добавлять по Id
    public void selectGroupById(int id) {
        select(By.xpath(".//*[@id='content']/form[2]/div[4]/select/option[@value='" + id + "']"));
    }

    public void chooseFilterGroupById(int id) {
        select(By.xpath(".//*[@id='right']/select/option[@value='" + id + "']"));
    }

    public void chooseFilterAllGroup() {
        select(By.xpath(".//*[@id='right']/select/option[@value='']"));
    }

    public void removeFromGroup() {
        click(By.name("remove"));
    }

    public void addInGroupSelectedContact() {
        click(By.name("add"));
    }

    public void AddContactInGroup(ContactData contact, GroupData group) {
        navigationHelper.HomePage();
        selectContactById(contact.getId());
        selectGroupById(group.getId());
        addInGroupSelectedContact();
    }

    public void deleteContactInGroup(ContactData contact, GroupData group) {
        navigationHelper.HomePage();
        chooseFilterGroupById(group.getId());
        selectContactById(contact.getId());
        removeFromGroup();
        navigationHelper.HomePage();
        chooseFilterAllGroup();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href = 'edit.php?id=%s']", id))).click();
    }

    public void initContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href = 'view.php?id=%s']", id))).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        acceptAlert();
    }

    public void deleteContactFromEdit() {
        click(By.xpath(".//*[@id='content']/form[2]/input[2]"));
    }

    public void submitContactModification() {
        click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        navigationHelper.AddNewContactPage();
        fillContactForm(contact, true);
        submitNewContactCreation();
        contactCache = null;
        returnToHomePage();

    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void deleteFromEdit(ContactData contact) {
        initContactModificationById(contact.getId());
        deleteContactFromEdit();
        contactCache = null;
        returnToHomePage();
    }

    public void returnToHomePage() {
        navigationHelper.HomePage();
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> elementDatas = element.findElements(By.tagName("td"));

            int id = Integer.parseInt(elementDatas.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstname = elementDatas.get(2).getText();
            String lastname = elementDatas.get(1).getText();
            String allPhones = elementDatas.get(5).getText();
            String allEmails = elementDatas.get(4).getText();
            String address = elementDatas.get(3).getText();

            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");

        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home)
                .withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2)
                .withEmail3(email3);
    }

    public String infoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        String allInfo = wd.findElement(By.id("content")).getText();
        String replacedAllInfo = allInfo.replace("Member of: test1", "").replaceAll("\n", " ")
                .replaceAll("[\\s]{2,}", " ").trim();
//        System.out.println(replacedAllInfo);
        wd.navigate().back();
        return replacedAllInfo;
    }
}
