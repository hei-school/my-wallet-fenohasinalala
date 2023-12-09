import java.time.Instant;
import java.time.LocalDate;

public class BankCard extends Item {
    private String bankName;
    private String offer;
    private String cardNumber;
    private LocalDate expirationDate;
    private String CVVNumber;
    private String cardHolderName;

    public BankCard(String owner, String bankName, String offer, String cardNumber, LocalDate expirationDate, String CVVNumber, String cardHolderName) {
        super(owner, "OK", Size.MEDIUM_AND_THICK);
        this.bankName = bankName;
        this.offer = offer;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVVNumber = CVVNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void viewItem() {
        // Implement logic to view BankCard details
    }
}
