
import java.lang.*;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String major;
    private String phoneNumber;
    private double gpa;
    private String dateOfBirth;

    public Student(int id, String firstName, String lastName, String major, String phoneNumber, double gpa,
            String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.phoneNumber = phoneNumber;
        this.gpa = gpa;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Roll No :" + id + "\t" + "First Name :" + firstName + "\t" + "LastName :" + lastName + "\t" + "Branch :" + major + "\t" + "Phone Number :" + phoneNumber + "\t" + "CGPA :" + gpa + "\t" + "Date Of Birth :" + dateOfBirth;
    }

    public int getRollNo() {
        return id;
    }
}
