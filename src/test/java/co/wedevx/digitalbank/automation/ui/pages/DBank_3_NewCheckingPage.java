package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;


import java.util.List;
import java.util.Map;

public class DBank_3_NewCheckingPage extends DBank_0_BaseMenuPage {

    public DBank_3_NewCheckingPage(WebDriver driver) throws InterruptedException {
        super(driver);

    }


    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointRadioButton;

    @FindBy(id = "name")
    private WebElement nameTxtBox;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement newCheckingSubmit;

    public double submitNewCheckingAccount(Map<String, String> newCheckingInfo) {
        // Extracting data from the map
        String accountType = newCheckingInfo.get("accountType");
        String ownership = newCheckingInfo.get("ownership");
        String accountName = newCheckingInfo.get("accountName");
        String initialDepositAmount = newCheckingInfo.get("initialDepositAmount");


        // Clicking on the appropriate radio button based on account type
        if ("Standard Checking".equalsIgnoreCase(accountType)) {
            standardCheckingRadioButton.click();
        } else if ("Interest Checking".equalsIgnoreCase(accountType)) {
            interestCheckingRadioButton.click();
        }

        // Clicking on the appropriate radio button based on ownership
        if ("Individual".equalsIgnoreCase(ownership)) {
            individualRadioButton.click();
        } else if ("Joint".equalsIgnoreCase(ownership)) {
            jointRadioButton.click();
        }

        // Filling the name text box with the provided account name
        nameTxtBox.sendKeys(accountName);

        // Filling the opening balance text box with the provided initial deposit amount
        openingBalanceTxtBox.sendKeys(initialDepositAmount);

        // Clicking the submit button
        newCheckingSubmit.click();

        // Waiting for success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-account-conf-alert")));

        return Double.parseDouble(initialDepositAmount);


    }

    public void confirmTheTransactions(List<BankTransaction> expectedTransactions) throws InterruptedException {
        // Clicking the last AccountCard to make the transactions visible in the table at the end
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//*[@id=\"firstRow\"]/div"));
        String lastCheckBoxNumber = String.valueOf(checkBoxes.size());
        String xPathForLastCheckBox = "//*[@id=\"firstRow\"]/div[" + lastCheckBoxNumber + "]/div/form/div/label/span[2]";
        WebElement lastCheckBoxBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathForLastCheckBox)));
        lastCheckBoxBtn.click();
        // After clicking and activating the last AccountCard, now the details of transactions are shown for that account at the end
        Thread.sleep(2000);
        List<WebElement> firstRowTransaction = driver.findElements(By.xpath("//table[@id='transactionTable']/tbody/tr[1]/td"));

        String actualDate = firstRowTransaction.get(0).getText();
        System.out.println("1. actualDate : " + actualDate);

        String actualCategory = firstRowTransaction.get(1).getText();
        System.out.println("2. actualCategory : " + actualCategory);

        String actualDescription = firstRowTransaction.get(2).getText();
        System.out.println("3. actualDescription : " + actualDescription);

        double actualAmount = Double.parseDouble(firstRowTransaction.get(3).getText().substring(1));
        System.out.println("4. actualAmount : " + actualAmount);

        double actualBalance = Double.parseDouble(firstRowTransaction.get(4).getText().substring(1));
        System.out.println("5. actualBalance : " + actualBalance);

//        //Assert that the actual transaction details match the expected values
//        BankTransaction expectedTransaction = expectedTransactions.get(0);
//        Assertions.assertEquals(expectedTransaction.getDate(), actualCategory, "Date mismatch");
//        Assertions.assertEquals(expectedTransaction.getCategory(), actualCategory, "Category mismatch");
//        Assertions.assertEquals(expectedTransaction.getDescription(), actualDescription, "Description mismatch");
//        Assertions.assertEquals(expectedTransaction.getAmount(), actualAmount, "Amount mismatch");
//        Assertions.assertEquals(expectedTransaction.getBalance(), actualBalance, "Balance mismatch");
    }

}

