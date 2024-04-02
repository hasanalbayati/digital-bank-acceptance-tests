package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class BrowserHelper {

    // Wait until the element is visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until the element is clickable and click on it
    public static WebElement waitUntilElementClickableAndClickOnIt(WebDriver driver, WebElement element, int timeToWaitInSec) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
        return clickableElement;
    }

    // Fluent Wait for Element Presence
    public static WebElement fluentWaitForElementPresence(WebDriver driver, WebElement element, int timeToWaitInSec) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeToWaitInSec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        return wait.until(d -> element);
    }

    // Scroll the web page to bring the specified element into view
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Click an element based on its text content
    public static void clickElementWithText(WebDriver driver, String text) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        if (!elements.isEmpty()) {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    element.click();
                    return;
                }
            }
            throw new RuntimeException("Element with text '" + text + "' is not visible.");
        } else {
            throw new RuntimeException("Element with text '" + text + "' not found.");
        }
    }

    // Fill a text input element with the provided value
    // Fill a text input element with the provided value
    public static void fillTextInput(WebDriver driver, WebElement inputElement, String value) {
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputElement);

        // Check if the element is interactable (visible and enabled)
        boolean isInteractable = inputElement.isDisplayed() && inputElement.isEnabled();
        if (!isInteractable) {
            throw new RuntimeException("Text input element is not interactable.");
        }

        // Clear any existing value and fill the input with the provided value
        inputElement.clear();
        inputElement.sendKeys(value);
    }

}
