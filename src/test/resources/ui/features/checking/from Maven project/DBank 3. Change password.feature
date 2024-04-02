Feature: Change Password

  Background:
    Given the user is on the "https://dbank-qa.wedevx.co/bank/login" homepage
    And the user enters email "xabi.alonso@realmadrid.es" and Password "Realmadrid@2015" to login
    When the user navigates to the Change Password page


  Scenario: Positive Case
    And the user enters current Password: "Realmadrid@2015", and enters a valid New Password: "Realmadrid@2000", Confirm New Password: "Realmadrid@2000" to login
    Then A Success message is displayed for the new Password


  Scenario: Negative Case 1
    And the user enters current Password: "Realmadrid@2015", and enters an invalid New Password: "2015", Confirm New Password: "2015" to login
    Then An Error message is displayed for invalid Password



  Scenario: Negative Case 2
    And the user enters current Password: "Realmadrid@2015", and enters a valid New Password: "Realmadrid@2015", and a valid Confirm New Password: "Realmadrid@2000" to login ( NEW PASSWORDS ARE VALID BUT DIFFERENT FROM EACH OTHER )
    Then An Error message is displayed for invalid Password
    #BUG DETECTED
  # The New Password Field and The Confirm Password Field accept valid Passwords even if they
  # are not identical and the first field ( New Password ) will be saved
  # as the new password without considering the second ( Confirm Password ) Field
