Feature: Create Checking

  Background:
    Given the user is on the "https://dbank-qa.wedevx.co/bank/login" homepage
    And the user enters email "xabi.alonso@realmadrid.es" and Password "Realmadrid@2015" to login
    When the user navigates to the New Checking page

  Scenario: Negative Case 1
    # The Account type will be left Blank to test
    And the user selects "Individual" Account Ownership
    And the user names the account "Football ticket"
    And The user makes the initial deposit of $600
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Negative Case 2
    And the user selects "Standard Checking" Account type
    # The Account Ownership will be left Blank to test
    And the user names the account "Football ticket 2"
    And The user makes the initial deposit of $200
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Negative Case 3
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    #The Account Name will be left Blank to test
    And The user makes the initial deposit of $200
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Negative Case 4
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    #The Account Name Field has a Max length of no longer than 256 characters
    And the user names the account "The Max length of Account Name Field consist of 256 characters"
    And The user makes the initial deposit of $200
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Negative Case 5
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    And the user names the account "Additional Living Costs"
    # The minimum deposit amount is 25$ on the opening stage, test it
    And The user makes the initial deposit of $24
    And the user clicks on submit
    Then An Error message is displayed indicates the right amount to be deposited

  Scenario: Negative Case 6
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    And the user names the account "Additional Living Costs"
    # The max deposit amount is a trillion $ on the opening stage, provide more
    And The user makes the initial deposit of $2000000000000
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Negative Case 7
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    And the user names the account "Additional Living Costs"
    #   The initial deposit Field accepts numbers only, provide some non-numeric values
    # The "" has been added to the line below, it indicates the "string" which is given
    And The user makes the initial deposit of $250
    And the user clicks on submit
    Then An Error Alert Message is displayed

  Scenario: Positive case for Reset
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    And the user names the account "Additional Living Costs"
    #   The initial deposit Field accepts numbers only, provide some non-numeric values
    # The "" has been added to the line below, it indicates the "string" which is given
    And The user makes the initial deposit of $250
    And The user resets the Fields
    Then The Fields should be empty

  Scenario: Checking Account Cards after submitting
    And the user selects "Standard Checking" Account type
    And the user selects "Individual" Account Ownership
    And the user names the account "Additional Living Costs"
    And The user makes the initial deposit of $7500
    And the user clicks on submit
    Then the user should see the green "Successfully created new Standard Checking account named Xabi Alonso Second Checking" message
    And the user should see newly added account card
      | accountName                 | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Xabi Alonso Second Checking | Standard Checking | Individual | 486134789     | 0.0%         | 100000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount    | balance   |
      | 2024-02-10 11:42 | Income   | 845326095 (DPT) - Deposit | 100000.00 | 100000.00 |



