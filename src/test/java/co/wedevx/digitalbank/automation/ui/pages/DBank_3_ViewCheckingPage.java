package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class DBank_3_ViewCheckingPage extends DBank_0_BaseMenuPage {

    public DBank_3_ViewCheckingPage(WebDriver driver) throws InterruptedException {
        super(driver);

    }


    public void validationOfTheNewAmountOperation(List<AccountCard> accountCardList) {
        checkingMenuBtn.click();
        viewCheckingMenuItemBtn.click();

        List<WebElement> allAccountCards = driver.findElements(By.xpath("//div[@id='firstRow']/div"));
        // The line above calls the AccountCard, but we need to select which one as shown in line below:
        // The line below gets the size ( total number of AccountCard ) subtracts 1 to reach to the last AccountCard
        WebElement NewAccountCard = allAccountCards.get(allAccountCards.size() - 1);

        String NewAccountCardFullText = NewAccountCard.getText();
        String actualAccountName = NewAccountCardFullText.substring(0, NewAccountCardFullText.indexOf("Account")).trim();
        String actualAccType = NewAccountCardFullText.substring(NewAccountCardFullText.indexOf("Account: ") + "Account: ".length(), NewAccountCardFullText.indexOf("Ownership")).trim();
        String actualOwnership = NewAccountCardFullText.substring(NewAccountCardFullText.indexOf("Ownership: ") + "Ownership: ".length(), NewAccountCardFullText.indexOf("Account Number")).trim();
        String actualAccountNumber = NewAccountCardFullText.substring(NewAccountCardFullText.indexOf("Account Number: ") + "Account Number: ".length(), NewAccountCardFullText.indexOf("Interest Rate")).trim();
        String actualInterestRate = NewAccountCardFullText.substring(NewAccountCardFullText.indexOf("Interest Rate: ") + "Interest Rate: ".length(), NewAccountCardFullText.indexOf("Balance")).trim().replace("%", "");
        String actualBalance = NewAccountCardFullText.substring(NewAccountCardFullText.indexOf("Balance: $")).trim();
        AccountCard expectedResult = accountCardList.get(0);

        Assertions.assertEquals(expectedResult.getAccountName(), actualAccountName);
        Assertions.assertEquals(expectedResult.getAccountType(), actualAccType);
        Assertions.assertEquals(expectedResult.getOwnership(), actualOwnership);
        Assertions.assertEquals(expectedResult.getInterestRate(), actualInterestRate + "%");
        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
        String actualBalanceValue = actualBalance.substring(actualBalance.indexOf("$") + 1).trim(); // Extracting numerical part
        Assertions.assertEquals(expectedBalance, actualBalanceValue); // Assertion on numerical balance
        System.out.println("///////////////////////////////////////////");

        // Waiting for success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-account-conf-alert")));
    }
}
