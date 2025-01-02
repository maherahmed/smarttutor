package de.hnu.controller;

import de.hnu.data.Course;
import de.hnu.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/deleteAll")
    public void deleteAllCourses() {
        courseService.deleteAllCourses();
    }

    @GetMapping("/delete/{id}")
    public void deleteCourseById(@PathVariable(value = "id") long id) {
        courseService.deleteCourseById(id);
    }

    @GetMapping("/byId/{id}")
    public Optional<Course> getCourseByID(@PathVariable(value = "id") long id) {
        return courseService.getCourseByID(id);
    }

    @PostMapping("/add")
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/addMaterial/{courseId}")
    public void addMaterialToCourse(
            @PathVariable(value = "courseId") long courseId,
            @RequestBody String json) {

        // Parse the JSON string manually (could be better with a small DTO object)
        JSONObject jsonObject = new JSONObject(json);
        String materialName = jsonObject.getString("materialName");
        String materialUrl = jsonObject.getString("materialUrl");

        courseService.addMaterialToCourse(courseId, materialName, materialUrl);
    }

    @GetMapping("/materialLink/{courseId}/{materialName}")
    public String getMaterialLinkByName(@PathVariable(value = "courseId") long courseID,
            @PathVariable(value = "materialName") String materialName) {
        return courseService.getMaterialLinkByName(courseID, materialName);
    }

    @GetMapping("/deleteMaterial/{courseId}/{materialName}")
    public String deletetMaterialByName(@PathVariable(value = "courseId") long courseID,
            @PathVariable(value = "materialName") String materialName) {
        return courseService.deleteMaterialByName(courseID, materialName);
    }

    @GetMapping("/changeDesc/{courseId}/{newDesc}")
    public void changeCourseDescription(@PathVariable(value = "courseId") long courseID,
            @PathVariable(value = "newDesc") String newDesc) {
        courseService.changeDescription(courseID, newDesc);
    }

}
