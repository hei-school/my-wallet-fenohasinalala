import java.time.Instant;

public class IDPhoto extends Item {
    private String description;

    public IDPhoto(String owner,  String description) {
        super(owner, "OK", Size.SMALL);
        this.description = description;
    }

    public void update() {
        // Implement logic to update IDPhoto details
    }

    @Override
    public void viewItem() {
        // Implement logic to view IDPhoto details
    }
}
