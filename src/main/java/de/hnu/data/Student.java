package de.hnu.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;


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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
    
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
