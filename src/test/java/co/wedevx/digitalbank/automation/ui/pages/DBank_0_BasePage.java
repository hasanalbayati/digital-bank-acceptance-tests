package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DBank_0_BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public DBank_0_BasePage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
}
