package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;

    private Driver() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getPropertiesValue("DBank.Browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;

                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    public static void takeScreenshot(Scenario scenario) {
        // Check if the scenario has failed and the driver supports taking screenshots
        if (scenario.isFailed() && driver instanceof TakesScreenshot screenshotDriver) {
            // Cast the driver to TakesScreenshot interface
            // Take the screenshot and store it as bytes
            byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            // Attach the screenshot to the scenario report with MIME type "image/png"
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

