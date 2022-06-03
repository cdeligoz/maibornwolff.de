@wip
Feature: Job Application Over the Website

  Background:
    Given applicant lands on the website

  Scenario: Application form should be accessible when an applicant applies a position
    When applicant accepts cookies
    And applicant navigates to "Karriere", "Offene Stellen"
    And the applicant picks "Berlin" from the dropdown menu
    And the applicant clicks on the "Software Testing", "Softwaretester / Softwaretesterin (w/m/d) Testautomatisierung"
    And the applicant clicks on the "Jetzt bewerben"
    Then the application form should open

  Scenario Outline:applicant should submit its application only with proper information
    When the applicant lands on the application form page
    And the applicant inputs its "<vorname>" , "<nachname>","<email>"
    And selects "männlich" gender from the dropdown
    And selects "Berlin" location from the dropdown
    And selects "LinkedIn" option from the dropdown menu
    And chooses their "German" skills level "Gute"
    And chooses their "English" skills level "Fließend"
    And uploads a "CV"
    And checks out the Datenschutz checkbox button
    And clicks on the Submit button
   # Then the application should be completed successfully
    Examples:
      | vorname | nachname | email          |
      | fake    | fake     | fake@gmail.com |
      | 123     | fake     | fake@gmail.com |
      | fake    | 456      | fake@gmail.com |
      | fake    | fake     | fake@gmail     |
      | %&      | fake     | fake@gmail.com |
      | fake    | [{       | fake@gmail.com |
      | fake    | fake     | fakegmail.com  |



