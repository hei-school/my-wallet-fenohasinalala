import java.time.Instant;
import java.util.HashMap;

public class Money extends Item {
    private Currency currency;
    private HashMap<Double, Integer> presentMoney;

    public Money(String owner, Currency currency) {
        super(owner, "OK", Size.LARGE);
        this.currency = currency;
        this.presentMoney = new HashMap<>();
        // Initialize other attributes
    }

    public void getTotalAmount() {
        // Implement logic to calculate total amount of money
    }

    public void addMoney(double valueToAdd, int numberToAdd) {
        // Implement logic to add money to the wallet
    }

    public void retireMoney(double valueToTake, int numberToTake) {
        // Implement logic to retire money from the wallet
    }

    @Override
    public void viewItem() {
        System.out.println("Type: Money");
        System.out.println("Currency: " + currency);
        System.out.println("Present Money:");
        presentMoney.forEach((key, value) ->
                System.out.println(key + " x " + value + " = " + (key * value)));

        System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
        System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
        System.out.println("Added date: " + super.getAddedDate());
        System.out.println("--------------");
    }
}
