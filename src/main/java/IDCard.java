import java.time.Instant;
import java.time.LocalDate;

public class IDCard extends Item {
    private String state;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String birthLocalisation;
    private String number;

    public IDCard(String owner, String state, String firstName, String lastName, LocalDate birthdate,
                  String birthLocalisation, String number) {
        super(owner, "OK", Size.MEDIUM);
        this.state = state;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.birthLocalisation = birthLocalisation;
        this.number = number;
    }

    // Additional optional attributes
    private String homeAddress;
    private String job;
    private String father;
    private String mother;
    private LocalDate creationDate;
    private String creationLocalisation;

    // Additional methods
    public void update(String firstName, String lastName, LocalDate birthdate,
                       String birthLocalisation, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.birthLocalisation = birthLocalisation;
        this.number = number;
    }

    // Getter and setter methods for optional attributes

    public String getState() {
        return state;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getBirthLocalisation() {
        return birthLocalisation;
    }

    public String getNumber() {
        return number;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationLocalisation() {
        return creationLocalisation;
    }

    public void setCreationLocalisation(String creationLocalisation) {
        this.creationLocalisation = creationLocalisation;
    }

    // Override viewItem() method
    @Override
    public void viewItem() {
        // Implement logic to display IDCard details
        // ...
    }


    @Override
    public String toString() {
        return "IDCard{" +
                "state='" + state + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", birthLocalisation='" + birthLocalisation + '\'' +
                ", number='" + number + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", job='" + job + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", creationDate=" + creationDate +
                ", creationLocalisation='" + creationLocalisation + '\'' +
                '}';
    }
}
