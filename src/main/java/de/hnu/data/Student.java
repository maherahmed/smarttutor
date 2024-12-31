package de.hnu.data;

public class Student {
    private long enrollmentNr;
    private String firstName;
    private String lastName;

    public Student(long enrollmentNr, String firstName, String lastName) {
        this.enrollmentNr = enrollmentNr;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public long getEnrollmentNr() {
        return enrollmentNr;
    }

    public void setEnrollmentNr(long enrollmentNr) {
        this.enrollmentNr = enrollmentNr;
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

}
