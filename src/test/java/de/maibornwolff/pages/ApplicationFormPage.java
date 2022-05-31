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

    @FindBy(css = "input[name='vorname']")
    public WebElement nameInputBox;

    @FindBy(css = "input[name='nachname']")
    public WebElement surnameInputBox;

    @FindBy(css = "input[name='e-mail']")
    public WebElement emailInputBox;

    @FindBy(xpath = "//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s']")
    public WebElement geschlechtArrow;

    @FindBy(xpath = "(//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s'])[2]")
    public WebElement locationArrow;

    @FindBy(xpath = "(//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s'])[3]")
    public WebElement whereDidYouHearUsFromArrow;

    @FindBy(xpath = "(//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s'])[4]")
    public WebElement germanLevelArrow;

    @FindBy(xpath = "(//*[@class='ui-button-icon-primary ui-icon ui-icon-caret-1-s'])[5]")
    public WebElement englishLevelArrow;

    @FindBy(xpath = "//input[@class='attachFileInputControl']")
    public WebElement uploadCvBox;

    @FindBy(xpath = "//label[contains(text(),'Hiermit akzeptiere ich die elektronische Speicher')]")
    public WebElement datenschutzCheckBox;

    @FindBy(css = ".submit")
    public WebElement submitButton;

    @FindBy(css = "#formSubmittedMessage")
    public WebElement formSubmittedMessage;

    public void selectOption(String option){
        BrowserUtils.waitForClickablility(By.xpath("//li[text()='"+option+"']"),5);
        Driver.get().findElement(By.xpath("//li[text()='"+option+"']")).click();
    }

    public void uploadCv(){
        String projectPath = System.getProperty("user.dir");
        String filePath = "src/test/resources/FakeCV.txt";
        String fullPath = projectPath + "/" + filePath;
        uploadCvBox.sendKeys(fullPath);

    }

    public boolean personalInformationCheck(String vorname, String nachname, String email){
        boolean flag=false;
        for(int i=0; i<vorname.length();i++){
            if(!Character.isAlphabetic(vorname.charAt(i))){
                flag=true;
                break;
            }
        }
        if(flag==false){
            for(int i=0; i<nachname.length();i++){
                if(!Character.isAlphabetic(nachname.charAt(i))){
                    flag=true;
                    break;
                }
            }
        }

        if(flag==false){
            Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
            Matcher m = p.matcher(email);

            if (!m.find())
            {
                flag=true;
            }



        }
        return flag;

    }

}
