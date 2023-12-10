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
        System.out.println("Description: " + DisplayUtils.formatNullOrEmptyValue(description));

        System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
        System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
        System.out.println("Added date: " + super.getAddedDate());
        System.out.println("--------------");
    }
}
