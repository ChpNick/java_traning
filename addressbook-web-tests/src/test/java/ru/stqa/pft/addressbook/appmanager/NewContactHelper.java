package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.AddressData;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class NewContactHelper extends HelperBase{

    public NewContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitNewContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(AddressData addressData) {
        type(By.name("firstname"), addressData.getFirstname());
        type(By.name("middlename"), addressData.getMiddlename());
        type(By.name("lastname"), addressData.getLastname());
        type(By.name("nickname"), addressData.getNickname());
        type(By.name("title"), addressData.getTitle());
        type(By.name("company"), addressData.getCompany());
        type(By.name("address"), addressData.getAddress());
        type(By.name("home"), addressData.getHome());
        type(By.name("mobile"), addressData.getMobile());
        type(By.name("work"), addressData.getWork());
        type(By.name("fax"), addressData.getFax());
        type(By.name("email"), addressData.getEmail());
        type(By.name("email2"), addressData.getEmail2());
        type(By.name("email3"), addressData.getEmail3());
        type(By.name("homepage"), addressData.getHomepage());

        select(By.xpath("//div[@id='content']/form/select[1]//option[" + (addressData.getBday() + 2) + "]"));
        select(By.xpath("//div[@id='content']/form/select[2]//option[" + (addressData.getBmons() + 2) + "]"));

        type(By.name("byear"), addressData.getByear());

        select(By.xpath("//div[@id='content']/form/select[3]//option[" + (addressData.getAday() + 2) + "]"));
        select(By.xpath("//div[@id='content']/form/select[4]//option[" + (addressData.getAmons() + 2) + "]"));

        type(By.name("ayear"), addressData.getAyear());

        type(By.name("address2"), addressData.getAddress2());
        type(By.name("phone2"), addressData.getPhone2());
        type(By.name("notes"), addressData.getNotes());
    }

}
