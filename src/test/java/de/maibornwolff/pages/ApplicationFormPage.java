package de.maibornwolff.pages;

import de.maibornwolff.utilities.BrowserUtils;
import de.maibornwolff.utilities.Driver;
import java.util.regex.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationFormPage {
    public ApplicationFormPage() {
        PageFactory.initElements(Driver.get(), this);
    }
    private static final String ARROW_ICON = "(//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s'])";
    @FindBy(css = "input[name='vorname']")
    public WebElement nameInputBox;

    @FindBy(css = "input[name='nachname']")
    public WebElement surnameInputBox;

    @FindBy(css = "input[name='e-mail']")
    public WebElement emailInputBox;

    @FindBy(xpath = ARROW_ICON + "[1]")
    public WebElement genderArrow;

    @FindBy(xpath = ARROW_ICON + "[2]")
    public WebElement locationArrow;

    @FindBy(xpath = ARROW_ICON + "[3]")
    public WebElement whereDidYouHearUsFromArrow;

    @FindBy(xpath = ARROW_ICON + "[4]")
    public WebElement germanLevelArrow;

    @FindBy(xpath = ARROW_ICON + "[5]")
    public WebElement englishLevelArrow;

    @FindBy(xpath = "//input[@class='attachFileInputControl']")
    public WebElement uploadCvBox;

    @FindBy(xpath = "//label[contains(text(),'Hiermit akzeptiere ich die elektronische Speicher')]")
    public WebElement privacyCheckBox;

    @FindBy(css = ".submit")
    public WebElement submitButton;

    @FindBy(css = "#formSubmittedMessage")
    public WebElement formSubmittedMessage;

    public void selectOption(String option) {
        BrowserUtils.waitForClickablility(By.xpath("//li[text()='" + option + "']"), 5);
        Driver.get().findElement(By.xpath("//li[text()='" + option + "']")).click();
    }
    public void uploadCv() {
        String projectPath = System.getProperty("user.dir");
        String filePath = "src/test/resources/FakeCV.txt";
        String fullPath = projectPath + "/" + filePath;
        uploadCvBox.sendKeys(fullPath);
    }
    public boolean personalInformationCheck(String name, String lastname, String email) {
        boolean flag = false;
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            for (int i = 0; i < lastname.length(); i++) {
                if (!Character.isAlphabetic(lastname.charAt(i))) {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
            Matcher m = p.matcher(email);
            if (!m.find()) {
                flag = true;
            }
        }
        return flag;
    }
    public void fillPersonalInformation(String name, String lastname, String email){
        nameInputBox.sendKeys(name);
        surnameInputBox.sendKeys(lastname);
        emailInputBox.sendKeys(email);
    }
}
