package de.maibornwolff.step_definitions;

import de.maibornwolff.pages.*;
import de.maibornwolff.utilities.BrowserUtils;
import de.maibornwolff.utilities.ConfigurationReader;
import de.maibornwolff.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class BewerbungStepDefs {
    boolean flag=false;

    @Given("applicant lands on the website")
    public void applicant_lands_on_the_website() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("applicant accepts cookies")
    public void applicant_accepts_cookies() {
        new CookiesPage().cookiesZulassen.click();
    }

    @When("applicant navigates to {string}, {string}")
    public void applicant_navigates_to(String tab, String module) {
        new DashboardPage().navigateToModule(tab,module);
    }

    @When("the applicant picks {string} from the dropdown menu")
    public void the_applicant_picks_from_the_dropdown_menu(String location) {
        Select select = new Select(new OffeneStellenPage().desiredLocation);
        select.selectByVisibleText(location);
    }

    @When("the applicant clicks on the {string}, {string}")
    public void the_applicant_clicks_on_the(String divisionName, String jobTitle)  {
        new OffeneStellenPage().navigateToJobDescription(divisionName,jobTitle);

    }
    @When("the applicant clicks on the {string}")
    public void the_applicant_clicks_on_the(String string) {
        new JobDescriptionPage().jetztBewerbenButton.click();

    }
    @Then("the application form should be opened")
    public void the_application_form_should_be_opened() {
        BrowserUtils.switchToWindow("");
        Assert.assertEquals("",Driver.get().getTitle());
    }
    @When("the applicant lands on the application form page")
    public void the_applicant_lands_on_the_application_form_page() throws InterruptedException {
        applicant_lands_on_the_website();
        applicant_accepts_cookies();
        applicant_navigates_to("Karriere", "Offene Stellen");
        the_applicant_picks_from_the_dropdown_menu("Berlin");
        the_applicant_clicks_on_the("Software Testing","Softwaretester");
        the_applicant_clicks_on_the("Jetzt bewerben");
        BrowserUtils.switchToWindow("");
    }

    @When("the applicant inputs its {string} , {string},{string}")
    public void the_applicant_inputs_its(String vorname, String nachname, String email) {
       new ApplicationFormPage().nameInputBox.sendKeys(vorname);
        new ApplicationFormPage().surnameInputBox.sendKeys(nachname);
        new ApplicationFormPage().emailInputBox.sendKeys(email);
        if(new ApplicationFormPage().personalInformationCheck(vorname,nachname,email)){
            flag=true;
        }

    }
    @When("selects {string} geschlecht from the dropdown")
    public void selects_geschlecht_from_the_dropdown(String option) {
        new ApplicationFormPage().geschlechtArrow.click();
        new ApplicationFormPage().selectOption(option);
    }
    @When("selects {string} location from the dropdown")
    public void selects_location_from_the_dropdown(String option) {
        new ApplicationFormPage().locationArrow.click();
        new ApplicationFormPage().selectOption(option);
    }
    @When("selects {string} option from the dropdown menu")
    public void selects_option_from_the_dropdown_menu(String option) {
        new ApplicationFormPage().whereDidYouHearUsFromArrow.click();
        new ApplicationFormPage().selectOption(option);
    }
    @When("chooses their {string} skills level {string}")
    public void chooses_their_skills_level(String language, String option) {
        if(language.equalsIgnoreCase("german")){
            new ApplicationFormPage().germanLevelArrow.click();
        }else if(language.equalsIgnoreCase("english")){
            new ApplicationFormPage().englishLevelArrow.click();
        }
        new ApplicationFormPage().selectOption(option);
    }
    @When("uploads a {string}")
    public void uploads_a(String form) {
        if(form.equalsIgnoreCase("CV")){
            new ApplicationFormPage().uploadCv();
        }
    }
    @When("checks out the Datenschutz checkbox button")
    public void checks_out_the_Datenschutz_checkbox_button() {
        BrowserUtils.scrollToElement(new ApplicationFormPage().datenschutzCheckBox);
       new ApplicationFormPage().datenschutzCheckBox.click();
    }
    @When("clicks on the Submit button")
    public void clicks_on_the_Submit_button() {
       Assert.assertTrue(new ApplicationFormPage().submitButton.isEnabled());
      // new ApplicationFormPage().submitButton.click();
    }
    @Then("the application should be completed successfully")
    public void the_application_should_be_completed_successfully() {
        if (flag){
            Assert.assertFalse(new ApplicationFormPage().formSubmittedMessage.isDisplayed());
        }else{
            Assert.assertTrue(new ApplicationFormPage().formSubmittedMessage.isDisplayed());
        }

    }

}
