package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.pages.DBank_0_HomePage;
import co.wedevx.digitalbank.automation.ui.pages.DBank_2_LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.DBank_3_NewCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.DBank_4_DepositPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class DBank_3_NewCheckingSteps {

    DBank_0_HomePage dBank_0_homePage = new DBank_0_HomePage(getDriver());
    DBank_2_LoginPage dBank_2_loginPage = new DBank_2_LoginPage(getDriver());
    DBank_3_NewCheckingPage dBank_3_checkingPage = new DBank_3_NewCheckingPage(getDriver());
    DBank_4_DepositPage dBank_4_depositPage = new DBank_4_DepositPage(getDriver());

    public DBank_3_NewCheckingSteps() throws InterruptedException {
    }

    @Given("user navigates to the Login Page and logs in with email: {string} and password: {string}.")
    public void userNavigatesToTheLoginPageAndLogsInWithEmailAndPassword(String userName, String password) throws InterruptedException {
//        getDriver().get(ConfigReader.getPropertiesValue("DBank_LoginPageUrl"));

        Assertions.assertEquals("https://dbank-qa.wedevx.co/bank/login", getDriver().getCurrentUrl(), "Login page not reached");

        dBank_2_loginPage.login(userName, password);

        Assertions.assertEquals("Digital Bank", getDriver().getTitle(), "Home page not reached");
    }

    @And("user navigates to the New Checking Page and creates a new checking account with following data:")
    public void userNavigatesToTheNewCheckingPageAndCreatesANewCheckingAccountWithValidAmount(List<Map<String, String>> checkingInfoList) throws InterruptedException {
        dBank_0_homePage.userNavigatesToCheckingPage();
        for (Map<String, String> checkingInfo : checkingInfoList) {
            dBank_3_checkingPage.submitNewCheckingAccount(checkingInfo);
        }
    }

    @And("user navigate to the Deposit page and selects the new account to process the deposit of ${double} amount and then validates that deposit after being redirected by the webpage to the View Checking Page")
    public void userNavigateToTheDepositPageAndSelectsTheNewAccountToProcessTheDepositOf$AmountAndThenValidatesThatDepositAfterBeingRedirectedByTheWebpageToTheViewCheckingPage(double amount) throws InterruptedException {
        dBank_0_homePage.userNavigatesToDepositPage();
        Thread.sleep(5000);

        dBank_4_depositPage.depositIntoAccount(amount);
        Thread.sleep(5000);

    }

    @Then("the user should see the following transactions")
    public void userShouldSeeTheTransactionsTable(List<BankTransaction> expectedTransactions) throws InterruptedException {
        dBank_3_checkingPage.confirmTheTransactions(expectedTransactions);

        System.out.println("The New Checking is successfully added to the Transactions");

    }
}


