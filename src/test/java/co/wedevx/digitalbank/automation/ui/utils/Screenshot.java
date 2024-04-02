package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    static final String SCREENSHOT_DIRECTORY = "screenshots";

    public static void takeScreenshot(Scenario scenario, TakesScreenshot driver) {
        if (scenario.isFailed()) {
            // Taking the screenshot
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            // Saving the screenshot to a file with date and time in the filename
            saveScreenshotToFile(screenshot);
        }
    }

    private static void saveScreenshotToFile(byte[] screenshot) {
        // Creating a SimpleDateFormat instance with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        // Generating a timestamp string based on the current date and time
        String timestamp = dateFormat.format(new Date());
        // Creating the directory if it doesn't exist
        File directory = new File(SCREENSHOT_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
        // Constructing the file path with the timestamp as part of the filename
        String fileName = SCREENSHOT_DIRECTORY + File.separator + "screenshot_" + timestamp + ".png";
        Path filePath = Paths.get(fileName);
        // Saving the screenshot to the file
        try {
            Files.write(filePath, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

