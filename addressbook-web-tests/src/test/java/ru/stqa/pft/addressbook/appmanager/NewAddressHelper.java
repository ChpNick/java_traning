package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.AddressData;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class NewAddressHelper {
    private FirefoxDriver wd;

    public NewAddressHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void submitNewAddressCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillAddressForm(AddressData addressData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(addressData.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(addressData.getMiddlename());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(addressData.getLastname());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(addressData.getNickname());
        wd.findElement(By.name("title")).click();
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(addressData.getTitle());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(addressData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(addressData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(addressData.getHome());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(addressData.getMobile());
        wd.findElement(By.name("work")).click();
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(addressData.getWork());
        wd.findElement(By.name("fax")).click();
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys(addressData.getFax());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(addressData.getEmail());
        wd.findElement(By.name("email2")).click();
        wd.findElement(By.name("email2")).clear();
        wd.findElement(By.name("email2")).sendKeys(addressData.getEmail2());
        wd.findElement(By.name("email3")).click();
        wd.findElement(By.name("email3")).clear();
        wd.findElement(By.name("email3")).sendKeys(addressData.getEmail3());
        wd.findElement(By.name("homepage")).click();
        wd.findElement(By.name("homepage")).clear();
        wd.findElement(By.name("homepage")).sendKeys(addressData.getHomepage());
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + (addressData.getBday() + 2) + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + (addressData.getBday() + 2) + "]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + (addressData.getBmons() + 2) + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + (addressData.getBmons() + 2) + "]")).click();
        }
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(addressData.getByear());

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[" + (addressData.getAday() + 2) + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[" + (addressData.getAday() + 2) + "]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[" + (addressData.getAmons() + 2) + "]")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[" + (addressData.getAmons() + 2) + "]")).click();
        }
        wd.findElement(By.name("ayear")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(addressData.getAyear());

        wd.findElement(By.name("address2")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys(addressData.getAddress2());
        wd.findElement(By.name("phone2")).click();
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(addressData.getPhone2());
        wd.findElement(By.name("notes")).click();
        wd.findElement(By.name("notes")).clear();
        wd.findElement(By.name("notes")).sendKeys(addressData.getNotes());
    }
}
