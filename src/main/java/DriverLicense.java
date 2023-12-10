public class DriverLicense extends Item {

  private String state;
  private DriverLicenseCategory category;

  public DriverLicense(String owner, String state, DriverLicenseCategory category) {
    super(owner, "OK", Size.MEDIUM_AND_THICK);
    this.state = state;
    this.category = category;
    // Initialize other attributes
  }

  public void update() {
    // Implement logic to update DriverLicense details
  }

  @Override
  public void viewItem() {
    System.out.println("Type: Driver License");
    System.out.println("State: " + DisplayUtils.formatNullOrEmptyValue(state));
    System.out.println(
        "Category: " + DisplayUtils.formatNullOrEmptyValue(String.valueOf(category)));

    System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
    System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
    System.out.println("Added date: " + super.getAddedDate());
    System.out.println("--------------");
  }
}
