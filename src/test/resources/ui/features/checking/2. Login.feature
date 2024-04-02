Feature: User Login

  Scenario: Successful login
    Given user navigates to the Login Page
    And user enters email: "j.belli@rmd.es" and password: "RealMadrid@2003" to login
    Then user logs in successfully