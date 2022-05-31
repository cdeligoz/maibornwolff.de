package de.maibornwolff.pages;

import de.maibornwolff.utilities.BrowserUtils;
import de.maibornwolff.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){

        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateToModule(String tab, String module) {
        WebElement tabElement = Driver.get().findElement(By.linkText(tab));
        BrowserUtils.hover(tabElement);
        Driver.get().findElement(By.linkText(module)).click();

    }
}
