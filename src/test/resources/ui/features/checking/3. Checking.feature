Feature: Create Checking

  Background:
    Given user navigates to the Login Page and logs in with email: "j.belli@rmd.es" and password: "RealMadrid@2003".

  Scenario: Deposit - positive case for Checking
    When user navigates to the New Checking Page and creates a new checking account with following data:
    # accountType1 : Standard Checking  ownership1: Individual
    # accountType2 : Interest Checking  ownership2: Joint
      | accountType       | ownership | accountName | initialDepositAmount |
      | Standard Checking | Joint     | Test8       | 55                   |
    And user navigate to the aDeposit page and selects the new account to process the deposit of $25.00 amount and then validates that deposit after being redirected by the webpage to the View Checking Page
    Then the user should see the following transactions
      | date             | category | description               | amount | balance |
      | 2024-02-24 21:55 | Income   | 845326363 (DPT) - Deposit | 55.00  | 80.00   |
