package co.wedevx.digitalbank.automation.ui.runners;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHandler {
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            // Check if there's an alert present
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            // No alert present
            return false;
        }
    }

    public static String handleAlert(WebDriver driver) {
        String alertText = "";
        if (isAlertPresent(driver)) {
            // Switch to the alert
            Alert alert = driver.switchTo().alert();
            // Get the alert text
            alertText = alert.getText();
            // Dismiss the alert
            alert.dismiss();
        }
        return alertText;
    }
}
