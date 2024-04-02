package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DBank_2_LoginPage extends DBank_0_BasePage {

    public DBank_2_LoginPage(WebDriver driver) throws InterruptedException {
        super(driver);

    }

    @FindBy(id = "username")
    private WebElement usernameTxtBox;
    @FindBy(id = "password")
    private WebElement passwordTxtBox;
    @FindBy(id = "remember-me")
    private WebElement remember_meCheckBox;
    @FindBy(xpath = "//button")
    private WebElement submitBtn;
    @FindBy(xpath = "//a[contains(text(),'Sign Up Here')]")
    private WebElement signUpHereLink;
    @FindBy(id = "page-title")
    private WebElement pageTitle;

    public void login(String username, String password) throws InterruptedException {
        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitBtn.click();
        Thread.sleep(3000);
    }

}