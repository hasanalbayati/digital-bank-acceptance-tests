package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// building a logic that reads the Config file ( properties file)
public class ConfigReader {
    private static Properties properties;


    //static initializer runs the block of code only once for the whole project
    //instance initializers runs the block of code once for every object creation from the class
    static {
        //filePath is the directory of your properties file
        String filePath = "src/test/resources/properties/DigitalBank.properties";

        //this is a class that enables you to read files
        //it throws a checked exception e

        FileInputStream input = null;

        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}
