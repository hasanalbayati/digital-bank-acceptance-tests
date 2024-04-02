package co.wedevx.digitalbank.automation.ui.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;


public class DBank_4_DepositPage extends DBank_0_BasePage {

    public DBank_4_DepositPage(WebDriver driver) throws InterruptedException {
        super(driver);

    }


    @FindBy(id = "selectedAccount")
    private WebElement selectedAccountDropDown;

    @FindBy(id = "amount")
    private WebElement amountTxtBox;

    @FindBy(xpath = "//i[@class='fa fa-dot-circle-o']")
    private WebElement submitButton;



    public void depositIntoAccount(double amount) throws InterruptedException {
        Thread.sleep(2000);
        Select selectAccount = new Select(selectedAccountDropDown);
        List<WebElement> options = selectAccount.getOptions();
        int lastIndex = options.size() - 1;
        selectAccount.selectByIndex(lastIndex);

        amountTxtBox.sendKeys(String.valueOf(amount));
        String expected = "$" + String.format("%.2f", amount); // 41
        Thread.sleep(2000);

        submitButton.click();
        Thread.sleep(2000);

        // Waiting for success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-title")));

        String actual = driver.findElement(By.xpath("//table[@id='transactionTable']/tbody/tr[1]/td[4]")).getText().trim();
        Assertions.assertEquals(expected, actual, "The amount deposited doesn't match the actual shown amount");

    }
}
