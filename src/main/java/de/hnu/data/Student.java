package de.hnu.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentNr;
    
    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    public Student(Long enrollmentNr, String firstName, String lastName) {
        this.enrollmentNr = enrollmentNr;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public Long getEnrollmentNr() {
        return enrollmentNr;
    }

    public void setEnrollmentNr(Long enrollmentNr) {
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
