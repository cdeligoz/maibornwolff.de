package de.maibornwolff.pages;

import de.maibornwolff.utilities.BrowserUtils;
import de.maibornwolff.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OffeneStellenPage extends BasePage{

    @FindBy(css="#edit-location")
    public WebElement desiredLocation;

    @FindBy(xpath = "//h3[normalize-space()='Software Testing']")
    public WebElement softwareTestingJobs;

    @FindBy(xpath = "//span[contains(text(),'Softwaretester')]")
    public WebElement softwaretesterJobTitle;

    public void navigateToJobDescription(String divisionName, String jobTitle)  {

      BrowserUtils.waitFor(1);
      Driver.get().findElement(By.xpath("//h3[normalize-space()='" + divisionName + "']")).click();
      Driver.get().findElement(By.xpath("//span[contains(text(),'"+jobTitle+"')]")).click();
    }
}
