@Registration
Feature: Digital Bank Registration Page


  Background:
    Given the user with the email "M.ty@Test.co" is not in the DB
    Given User navigates to Digital Bank signup page

  @Test
  Scenario: Registering a new Client with personal Data at Digital Bank - Positive Case
    When user creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email        | password        | address      | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Mike      | Tyson    | M      | 01/01/1965 | 125-45-2464 | M.ty@Test.co | RealMadrid@1981 | Bernabeu str | Madrid   | Madrid | 55555      | Spain   | 2675678352 | 2675678344  | 2675678333 | true           |
    Then user should be displayed with message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email        | password        | address      | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Mr.   | Mike      | Tyson    | M      | 01/01/1965 | 125-45-2464 | M.ty@Test.co | RealMadrid@1981 | Bernabeu str | Madrid   | Madrid | 55555      | Spain   | 2675678352 | 2675678344  | 2675678333 | true              | true             | true                  | true    |

  @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank Admin, i want to make sure users can not register without providing all valid data
    When user creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |

    Then the user should see the "<fieldWithError>" required field error message "<errorMessage>"
    Examples:
      | title | firstName | lastName | gender | dob | ssn | email | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckMark | fieldWithError | errorMessage                |
      |       |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | title          | Select an item in the list  |
      | Mr.   |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | firstName      | Fill out this field         |
      | Mr.   | Jack      |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | lastName       | Fill out this field         |
      | Mr.   | Jack      | Mike     |        |     |     |       |          |         |          |        |            |         |           |             |           |                | gender         | Select one of these options |
