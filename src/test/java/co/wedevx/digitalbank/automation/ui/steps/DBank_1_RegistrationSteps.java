package co.wedevx.digitalbank.automation.ui.steps;

import ch.qos.logback.core.db.dialect.DBUtil;
import co.wedevx.digitalbank.automation.ui.pages.DBank_1_RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DBank_1_RegistrationSteps {
    DBank_1_RegistrationPage dBank_1_registrationPage = new DBank_1_RegistrationPage(getDriver());
    List<Map<String, Object>> nextValList = new ArrayList<>();

    public DBank_1_RegistrationSteps() throws InterruptedException {
    }

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("DBank_RegistrationPageUrl"));
        assertEquals("Digital Bank", getDriver().getTitle(), "Registration page Title mismatch");
    }

    @When("user creates account with following fields")
    public void user_creates_account_with_following_fields(List<Map<String, String>> registrationTestDataListMap) throws InterruptedException {
        dBank_1_registrationPage.fillOutRegistrationForm(registrationTestDataListMap);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")));
    }

    @Then("user should be displayed with message {string}")
    public void user_should_be_displayed_with_message(String expectedSuccessMessage) {
        assertEquals(expectedSuccessMessage, dBank_1_registrationPage.getMessage(), "Success Message Mismatch");
    }

    @Then("the user should see the {string} required field error message {string}")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String field, String arg1) {
    }

    @Then("the following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileInfoInDBList) {
        Map<String, String> expectedUserInfoMap = expectedUserProfileInfoInDBList.get(0);
        String queryUserTable = String.format("SELECT * FROM users WHERE username='%s'",expectedUserInfoMap.get("email"));
        String queryUserProfile = String.format("SELECT * FROM user_profile WHERE email_address='%s'",expectedUserInfoMap.get("email"));


        List<Map<String, Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryUserTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfile);


        assertEquals(1, actualUserInfoList.size(), "registration generated unexpected number of users");
        assertEquals(1, actualUserProfileInfoList.size(), "registration generated unexpected number of user profiles");

        Map<String, Object> actualUserInfoMap = actualUserInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);

        assertEquals(expectedUserInfoMap.get("title"), actualUserProfileInfoMap.get("title"), "registration generated wrong title");
        assertEquals(expectedUserInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "registration generated wrong firstName");
        assertEquals(expectedUserInfoMap.get("lastName"), actualUserProfileInfoMap.get("last_name"), "registration generated wrong lastName");
        assertEquals(expectedUserInfoMap.get("gender"), actualUserProfileInfoMap.get("gender"), "registration generated wrong gender");
        //assertEquals(expectedUserInfoMap.get("dob"), actualUserProfileInfoMap.get("dob"), "registration generated wrong Date of Birth");
        assertEquals(expectedUserInfoMap.get("ssn"), actualUserProfileInfoMap.get("ssn"), "registration generated wrong SSN");
        assertEquals(expectedUserInfoMap.get("email"), actualUserProfileInfoMap.get("email_address"), "registration generated wrong email");
        assertEquals(expectedUserInfoMap.get("address"), actualUserProfileInfoMap.get("address"), "registration generated wrong address");
        assertEquals(expectedUserInfoMap.get("locality"), actualUserProfileInfoMap.get("locality"), "registration generated wrong locality");
        assertEquals(expectedUserInfoMap.get("region"), actualUserProfileInfoMap.get("region"), "registration generated wrong region");
        assertEquals(expectedUserInfoMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "registration generated wrong postal Code");
        assertEquals(expectedUserInfoMap.get("country"), actualUserProfileInfoMap.get("country"), "registration generated wrong country");
        assertEquals(expectedUserInfoMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "registration generated wrong homePhone");
        assertEquals(expectedUserInfoMap.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"), "registration generated wrong mobilePhone");
        assertEquals(expectedUserInfoMap.get("workPhone"), actualUserProfileInfoMap.get("work_phone"), "registration generated wrong workPhone");


        //Validate users table
        assertEquals(expectedUserInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "accountNonExpired mismatch upon Registration");
        assertEquals(expectedUserInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "accountNonLocked mismatch upon Registration");
        assertEquals(expectedUserInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "credentialsNonExpired mismatch upon Registration");
        assertEquals(expectedUserInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "account enabled mismatch upon Registration");
        assertEquals(expectedUserInfoMap.get("email"), actualUserInfoMap.get("username"), "username mismatch upon Registration");
        assertEquals(nextValList.get(0).get("next_val"), actualUserInfoMap.get("id"), "ID Mismatch");

        long expectedUserProfileId = Integer.parseInt(String.valueOf(nextValList.get(0).get("next_val")));
        assertEquals(++expectedUserProfileId, actualUserProfileInfoMap.get("id"), "ID Mismatch");

    }



    @Given("the user with the email {string} is not in the DB")
    public void theUserWithTheEmailIsNotInTheDB(String email) {
        String queryForUserProfile = String.format("DELETE FROM user_profile WHERE email_address='%s'", email);
        String queryForUsers = String.format("DELETE FROM users WHERE username='%s'", email);


        String queryToGetNextValInHibernateSeqTable = String.format("SELECT * FROM hibernate_sequence");
        nextValList = DBUtils.runSQLSelectQuery(queryToGetNextValInHibernateSeqTable);


        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);

    }
}