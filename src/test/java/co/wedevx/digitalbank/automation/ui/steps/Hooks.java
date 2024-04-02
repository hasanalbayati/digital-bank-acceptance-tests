package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {


    @Before("@Registration")
    public void establishConnectionToDB (){
        DBUtils.establishConnection();
    }

    @Before("not @Registration")
    public void the_user_is_on_dbank_loginpage(){
        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
    }


    @After
    public void afterEachScenario(Scenario scenario) {
        Driver.takeScreenshot(scenario);
        Driver.quitDriver();
    }

    @After()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }


}
