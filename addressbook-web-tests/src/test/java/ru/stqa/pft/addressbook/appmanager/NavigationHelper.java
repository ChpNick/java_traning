package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if (isElemebtPresent(By.xpath(".//*[@id=\"content\"]/h1"))
                && wd.findElement(By.xpath(".//*[@id=\"content\"]/h1")).getText().equals("Groups")
                && isElemebtPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        if (isElemebtPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoAddNewContactPage() {
        if (isElemebtPresent(By.xpath(".//*[@id='content']/h1"))
                && wd.findElement(By.xpath(".//*[@id='content']/h1")).getText().equals("Edit / add address book entry")
                && isElemebtPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }
}
