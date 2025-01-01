package de.hnu.service;

import de.hnu.StudentController;
import de.hnu.data.Student;
import de.hnu.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    private static final Logger logger = LogManager.getLogger(StudentController.class);

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        logger.info("Saving student: getEnrollmentNr {}", student.getEnrollmentNr());
        logger.info("Saving student: getEnrollmentNr {}", student.getFirstName());
        logger.info("Saving student: getEnrollmentNr {}", student.getLastName());
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentByID(long enrollmentNr) {
        return studentRepository.findById(enrollmentNr);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
}