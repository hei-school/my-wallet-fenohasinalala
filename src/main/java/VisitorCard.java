public class VisitorCard extends Item {
    private String name;
    private String email;
    private String phoneNumber;
    private String website;

    public VisitorCard(String owner, String name, String email, String phoneNumber, String website) {
        super(owner);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.website = website;
        // Initialize other attributes
    }

    public void update() {
        // Implement logic to update VisitorCard details
    }

    @Override
    public void viewItem() {
        // Implement logic to view VisitorCard details
    }
}
