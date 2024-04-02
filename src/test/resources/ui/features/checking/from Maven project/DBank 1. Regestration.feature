Feature: Register a new Client at the DBank

  Scenario: Register a new Client at the DBank
    Given the user is on the "https://dbank-qa.wedevx.co/bank/login" homepage
    Given the user navigates to the Sign Up Page
    When the user enters the personal data:
      | Title | FirstName | LastName | Gender | DateOfBirth | SocialSecurityNumber | Email                  | Password        | PasswordConfirmation | PasswordForLogin |
      | Mr.   | Sam       | Mart     | M      | 01/01/1985  | 776796974            | sam.mart@realmadrid.es | RealMadrid@1981 | RealMadrid@1981      | RealMadrid@1981  |
    And the user enters the address data:
      | Address             | Locality  | Region | PostalCode | Country | HomePhone  | MobilePhone | WorkPhone  |
      | 123 Bernabeu street | Salamanca | Madrid | 78111      | Spain   | 5473925436 | 5473925436  | 5473925436 |
    Then the user should be on Login page

