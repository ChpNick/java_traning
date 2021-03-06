package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
        if (isElementPresent(By.xpath(".//*[@id=\"content\"]/h1"))
                && wd.findElement(By.xpath(".//*[@id=\"content\"]/h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void HomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void AddNewContactPage() {
        if (isElementPresent(By.xpath(".//*[@id='content']/h1"))
                && wd.findElement(By.xpath(".//*[@id='content']/h1")).getText().equals("Edit / add address book entry")
                && isElementPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }
}
