package de.hnu;

import de.hnu.data.Student;
import de.hnu.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public  Optional<Student> getStudentByID(@PathVariable(value = "id")long id) {
        return studentService.getStudentByID(id);
    }

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
