package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.DBank_2_LoginPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class DBank_2_LoginSteps {
    DBank_2_LoginPage dBank_2_loginPage = new DBank_2_LoginPage(getDriver());

    public DBank_2_LoginSteps() throws InterruptedException {
    }

    @Given("user navigates to the Login Page")
    public void userNavigatesToTheLoginPage() {
        getDriver().get(ConfigReader.getPropertiesValue("DBank_LoginPageUrl"));
        Assertions.assertEquals("https://dbank-qa.wedevx.co/bank/login", getDriver().getCurrentUrl(), "Login page not reached");
    }

    @And("user enters email: {string} and password: {string} to login")
    public void userEntersEmailAndToLogin(String userName, String password) throws InterruptedException {
        dBank_2_loginPage.login(userName, password);
    }

    @Then("user logs in successfully")
    public void userLogsInSuccessfully() {
        Assertions.assertEquals("Digital Bank", getDriver().getTitle(), "Home page not reached");
    }
}
