package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DBank_0_HomePage extends DBank_0_BasePage {

    public DBank_0_HomePage(WebDriver driver) throws InterruptedException {
        super(driver);

    }

    @FindBy(id = "checking-menu")
    private WebElement checkingMenu;
    @FindBy(id = "new-checking-menu-item")
    private WebElement newCheckingButton;
    @FindBy(id = "deposit-menu-item")
    private WebElement depositMenuItem;

    public void userNavigatesToCheckingPage() throws InterruptedException {
        checkingMenu.click();
        newCheckingButton.click();
        Thread.sleep(3000);
    }

    public void userNavigatesToDepositPage() throws InterruptedException {
        depositMenuItem.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectedAccount")));
        Thread.sleep(2000);

    }
}