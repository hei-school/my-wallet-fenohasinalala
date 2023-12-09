import java.time.LocalDate;

public class IDCard extends Item {
    private String state;
    private String firstName;
    private String lastName;

    public IDCard(String owner, String state, String firstName, String lastName) {
        super(owner);
        this.state = state;
        this.firstName = firstName;
        this.lastName = lastName;
        // Initialize other attributes
    }

    public void update(String firstName, String lastName, LocalDate birthdate, String birthLocalisation, String number) {
        // Implement logic to update IDCard details
    }

    @Override
    public void viewItem() {
        // Implement logic to view IDCard details
    }
}
