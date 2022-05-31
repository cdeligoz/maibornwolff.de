package de.maibornwolff.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobDescriptionPage extends BasePage{

    @FindBy(linkText = "Jetzt bewerben")
    public WebElement jetztBewerbenButton;
}
