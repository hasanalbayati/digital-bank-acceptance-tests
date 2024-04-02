package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DBank_0_BaseMenuPage extends DBank_0_BasePage {
    public DBank_0_BaseMenuPage(WebDriver driver) throws InterruptedException {
        super(driver);
    }

    @FindBy(id = "checking-menu")
    protected WebElement checkingMenuBtn;
    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingButton;
    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingMenuItemBtn;

    @FindBy(id = "home-menu-item")
    protected WebElement homeButton;

    public void goToHomePage(){
        homeButton.click();
    }
}
