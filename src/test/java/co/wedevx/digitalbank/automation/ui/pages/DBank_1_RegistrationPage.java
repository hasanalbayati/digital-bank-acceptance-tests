package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v119.css.model.Value;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DBank_1_RegistrationPage extends DBank_0_BasePage {

    public DBank_1_RegistrationPage(WebDriver driver) throws InterruptedException {
        super(driver);

    }

    MockData mockData = new MockData();

    @FindBy(id = "title")
    private WebElement titleDropDown;
    @FindBy(id = "firstName")
    private WebElement firstNameTxt;
    @FindBy(id = "lastName")
    private WebElement lastNameTxt;
    @FindBy(xpath = "//label[@for='male']//input")
    private WebElement genderMRadio;
    @FindBy(xpath = "//label[@for='female']//input")
    private WebElement genderFRadio;
    @FindBy(id = "dob")
    private WebElement dobTxt;
    @FindBy(id = "ssn")
    private WebElement ssnTxt;
    @FindBy(id = "emailAddress")
    private WebElement emailAddressTxt;
    @FindBy(id = "password")
    private WebElement passwordTxt;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTxt;
    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    private WebElement nextBtn;
    @FindBy(id = "address")
    private WebElement addressTxt;
    @FindBy(id = "locality")
    private WebElement localityTxt;
    @FindBy(id = "region")
    private WebElement regionTxt;
    @FindBy(id = "postalCode")
    private WebElement postalCodeTxt;
    @FindBy(id = "country")
    private WebElement countryTxt;
    @FindBy(id = "homePhone")
    private WebElement homePhoneTxt;
    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxt;
    @FindBy(id = "workPhone")
    private WebElement workPhoneTxt;
    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckBox;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerBtn;
    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement messageLabel;

    public void fillOutRegistrationForm(List<Map<String, String>> registrationPageTestDataListOfMaps) throws InterruptedException {
        Select titleSelect = new Select(titleDropDown);
        Map<String, String> firstRow = registrationPageTestDataListOfMaps.get(0);
        titleSelect.selectByVisibleText(firstRow.get("title"));

        firstNameTxt.sendKeys(firstRow.get("firstName"));
        lastNameTxt.sendKeys(firstRow.get("lastName"));

        if (firstRow.get("gender").equalsIgnoreCase("M")) {
            genderMRadio.click();
        } else if (firstRow.get("gender").equalsIgnoreCase("F")) {
            genderFRadio.click();
        } else {
            System.out.println("Wrong Gender");
        }

        if (firstRow.get("dob") != null) {
            dobTxt.sendKeys(firstRow.get("dob"));
        }

        if (firstRow.get("ssn") != null) {
            ssnTxt.sendKeys(firstRow.get("ssn"));
        }

        if (firstRow.get("email") != null) {
            emailAddressTxt.sendKeys(firstRow.get("email"));
        }

        if (firstRow.get("password") != null) {
            passwordTxt.sendKeys(firstRow.get("password"));
            confirmPasswordTxt.sendKeys(firstRow.get("password"));
        }

        Thread.sleep(4000);

        nextBtn.click();

        // Waiting for next Registration Page to open then precedes
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address")));

        if (firstRow.get("address") != null) {
            addressTxt.sendKeys(firstRow.get("address"));
        }

        if (firstRow.get("locality") != null) {
            localityTxt.sendKeys(firstRow.get("locality"));
        }

        if (firstRow.get("region") != null) {
            regionTxt.sendKeys(firstRow.get("region"));
        }

        if (firstRow.get("postalCode") != null) {
            postalCodeTxt.sendKeys(firstRow.get("postalCode"));
        }
        if (firstRow.get("country") != null) {
            countryTxt.sendKeys(firstRow.get("country"));
        }
        if (firstRow.get("homePhone") != null) {
            homePhoneTxt.sendKeys(firstRow.get("homePhone"));
        }
        if (firstRow.get("mobilePhone") != null) {
            mobilePhoneTxt.sendKeys(firstRow.get("mobilePhone"));
        }
        if (firstRow.get("workPhone") != null) {
            workPhoneTxt.sendKeys(firstRow.get("workPhone"));
        }

        agreeTermsCheckBox.click();
        registerBtn.click();

        Thread.sleep(2000);

    }

    public String getMessage() {
        return messageLabel.getText().replaceAll("\\s+", " ").trim().replace(" ×", "");
    }
}