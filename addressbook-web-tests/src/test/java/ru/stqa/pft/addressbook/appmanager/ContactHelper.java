package ru.stqa.pft.addressbook.appmanager;

import org.apache.xalan.xsltc.dom.SimpleResultTreeImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class ContactHelper extends HelperBase {
    private final NavigationHelper navigationHelper;

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
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());

        select(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.getBday() + 2) + "]"));
        select(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.getBmons() + 2) + "]"));

        type(By.name("byear"), contactData.getByear());

        select(By.xpath("//div[@id='content']/form/select[3]//option[" + (contactData.getAday() + 2) + "]"));
        select(By.xpath("//div[@id='content']/form/select[4]//option[" + (contactData.getAmons() + 2) + "]"));

        type(By.name("ayear"), contactData.getAyear());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        acceptAlert();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@title = 'Edit']")).get(index).click();
//        click(By.xpath("//img[@title = 'Edit']"));
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

    public void createContact(ContactData contact) {
        navigationHelper.gotoAddNewContactPage();
        fillContactForm(contact, true);
        submitNewContactCreation();
        navigationHelper.gotoHomePage();

    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));

        for (WebElement element: elements){
            List<WebElement> elementDatas = element.findElements(By.tagName("td"));

            int id = Integer.parseInt(elementDatas.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstname = elementDatas.get(2).getText();
            String lastname = elementDatas.get(1).getText();

            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null, null, null,
                    null, null, null, null, null, 0, 0, null, 0, 0, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
