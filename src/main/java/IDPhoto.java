public class IDPhoto extends Item {
    private int number;
    private String description;

    public IDPhoto(String owner, int number, String description) {
        super(owner);
        this.number = number;
        this.description = description;
        // Initialize other attributes
    }

    public void update() {
        // Implement logic to update IDPhoto details
    }

    @Override
    public void viewItem() {
        // Implement logic to view IDPhoto details
    }
}
