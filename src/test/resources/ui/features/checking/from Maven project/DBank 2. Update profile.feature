Feature: Update profile

  Background:
    Given the user is on the "https://dbank-qa.wedevx.co/bank/login" homepage
    And the user enters email "xabi.alonso@realmadrid.es" and Password "Realmadrid@2015" to login
    And the user navigates to the Update Profile page

  Scenario: Positive case
    When the user makes some updates to own profile
    Then the user sees a success message

  Scenario: Negative case
    When the user makes some invalid updates to own profile
    Then the user sees an error message and the data are not saved

  Scenario: Positive case for Reset
    When the user makes some updates to own profile and Reset it
    Then the user sees same texts in the fields and the data are not saved after pressing Reset