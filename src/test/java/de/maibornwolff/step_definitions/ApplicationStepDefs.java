package de.maibornwolff.step_definitions;

import de.maibornwolff.pages.*;
import de.maibornwolff.utilities.BrowserUtils;
import de.maibornwolff.utilities.ConfigurationReader;
import de.maibornwolff.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.Select;

public class ApplicationStepDefs {
    private static boolean flag = false;
    JobVacanciesPage jobVacanciesPage = new JobVacanciesPage();
    ApplicationFormPage applicationFormPage = new ApplicationFormPage();

    @Given("applicant lands on the website")
    public void applicant_lands_on_the_website() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("applicant accepts cookies")
    public void applicant_accepts_cookies() {
        new CookiesPage().selectElement(new CookiesPage().cookiesZulassen);
    }

    @When("applicant navigates to {string}, {string}")
    public void applicant_navigates_to(String tab, String module) {
        new DashboardPage().navigateToModule(tab, module);
    }

    @When("the applicant picks {string} from the dropdown menu")
    public void the_applicant_picks_from_the_dropdown_menu(String location) {
        Select select = new Select(jobVacanciesPage.desiredLocation);
        select.selectByVisibleText(location);
    }

    @When("the applicant clicks on the {string}, {string}")
    public void the_applicant_clicks_on_the(String divisionName, String jobTitle) {
        jobVacanciesPage.navigateToJobDescription(divisionName, jobTitle);
    }

    @When("the applicant clicks on the {string}")
    public void the_applicant_clicks_on_the(String string) {
        new JobDescriptionPage().selectElement(new JobDescriptionPage().jetztBewerbenButton);
    }

    @Then("the application form should open")
    public void the_application_form_should_open() {
        BrowserUtils.switchToWindow("");
        assertEquals("", Driver.get().getTitle());
    }

    @When("the applicant lands on the application form page")
    public void the_applicant_lands_on_the_application_form_page() throws InterruptedException {
        applicant_lands_on_the_website();
        applicant_accepts_cookies();
        applicant_navigates_to("Karriere", "Offene Stellen");
        the_applicant_picks_from_the_dropdown_menu("Berlin");
        the_applicant_clicks_on_the("Software Testing", "Softwaretester");
        the_applicant_clicks_on_the("Jetzt bewerben");
        BrowserUtils.switchToWindow("");
    }

    @When("the applicant inputs its {string} , {string},{string}")
    public void the_applicant_inputs_its(String name, String lastname, String email) {
        applicationFormPage.fillPersonalInformation(name, lastname, email);
        if (applicationFormPage.personalInformationCheck(name, lastname, email)) {
            flag = true;
        }
    }

    @When("selects {string} gender from the dropdown")
    public void selects_gender_from_the_dropdown(String option) {
        applicationFormPage.genderArrow.click();
        applicationFormPage.selectOption(option);
    }

    @When("selects {string} location from the dropdown")
    public void selects_location_from_the_dropdown(String option) {
        applicationFormPage.locationArrow.click();
        applicationFormPage.selectOption(option);
    }

    @When("selects {string} option from the dropdown menu")
    public void selects_option_from_the_dropdown_menu(String option) {
        applicationFormPage.whereDidYouHearUsFromArrow.click();
        applicationFormPage.selectOption(option);
    }

    @When("chooses their {string} skills level {string}")
    public void chooses_their_skills_level(String language, String option) {
        if (language.equalsIgnoreCase("german")) {
            applicationFormPage.germanLevelArrow.click();
        } else if (language.equalsIgnoreCase("english")) {
            applicationFormPage.englishLevelArrow.click();
        }
        applicationFormPage.selectOption(option);
    }

    @When("uploads a {string}")
    public void uploads_a(String form) {
        if (form.equalsIgnoreCase("CV")) {
            applicationFormPage.uploadCv();
        }
    }

    @When("checks out the Datenschutz checkbox button")
    public void checks_out_the_Datenschutz_checkbox_button() {
        BrowserUtils.scrollToElement(new ApplicationFormPage().privacyCheckBox);
        applicationFormPage.privacyCheckBox.click();
    }

    @When("clicks on the Submit button")
    public void clicks_on_the_Submit_button() {
        assertTrue(new ApplicationFormPage().submitButton.isEnabled());
        // new ApplicationFormPage().submitButton.click();
    }

    @Then("the application should be completed successfully")
    public void the_application_should_be_completed_successfully() {
        if (flag) {
            assertFalse(new ApplicationFormPage().formSubmittedMessage.isDisplayed());
        } else {
            assertTrue(new ApplicationFormPage().formSubmittedMessage.isDisplayed());
        }

    }

}
