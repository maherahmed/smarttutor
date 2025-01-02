package de.hnu.controller;

import de.hnu.data.Course;
import de.hnu.data.Student;
import de.hnu.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/deleteAll")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    @GetMapping("/byId/{id}")
    public Optional<Student> getStudentByID(@PathVariable(value = "id") long id) {
        return studentService.getStudentByID(id);
    }

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled in course successfully!");
    }

    @GetMapping("/{studentId}/courses")
    public List<Course> getCourses(@PathVariable Long studentId) {
    Student student = studentService.getStudentByID(studentId)
        .orElse(null);
        //.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    return student.getCourses();
    }

}
