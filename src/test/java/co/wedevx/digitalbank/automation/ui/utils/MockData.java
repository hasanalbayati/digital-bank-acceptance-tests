package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.cucumber.java.it.Ma;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {

    private FakeValuesService fakeValueService = new FakeValuesService(
            new Locale("en-US"), new RandomService());



    public Map<String, String> generateRandomEmail() {
        String name = fakeValueService.bothify(new Faker().name().lastName());
        String email = fakeValueService.bothify(name + "###@gmail.com");

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);

        return data;
    }

    public String generateRandomSsn(){
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }
}
