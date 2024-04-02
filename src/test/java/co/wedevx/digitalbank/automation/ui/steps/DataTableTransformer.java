package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public BankTransaction bankTransactionEntry(Map<String, String> entry) {
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));
        return new BankTransaction(date, category, description, amount, balance);
    }
}
