package de.hnu.service;

import de.hnu.controller.CourseController;
import de.hnu.data.Course;
import de.hnu.repository.CourseRepository;
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
public class CourseService {

    private final CourseRepository courseRepository;
    private static final Logger logger = LogManager.getLogger(CourseController.class);

    public CourseService(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        logger.info("Saving course: getId {}", course.getId());
        logger.info("Saving course: getTitle {}", course.getTitle());
        logger.info("Saving course: getDescription {}", course.getDescription());
        return courseRepository.save(course);
    }

    public Optional<Course> getCourseByID(long courseID) {
        return courseRepository.findById(courseID);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }

    public void deleteCourseById(long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }

    @Transactional
    public Course addMaterialToCourse(long courseID, String materialName, String materialUrl) {
        Course course = getCourseByID(courseID).orElse(null);
        if (course != null) {
            course.addMaterial(materialName, materialUrl);
            // course.getMaterials().put("AI Applications",
            // "http://example.com/ai-applications.pdf");

        }
        return courseRepository.save(course);

    }

    public Course changeDescription(long courseId, String newDesc) {
        Course course = getCourseByID(courseId).orElse(null);
        if (course != null) {
            course.setDescription(newDesc);
        }
        logger.info("Saving course: getId {}", course.getId());
        logger.info("Saving course: getTitle {}", course.getTitle());
        logger.info("Saving course: getDescription {}", course.getDescription());

        return courseRepository.save(course);
    }

    public String getMaterialLinkByName(long courseID, String materialName) {
        if (courseRepository.existsById(courseID)) {
            return getCourseByID(courseID).orElse(null).getMaterialLinkByName(materialName);
        }
        return "No Course with this ID is found";
    }

    public String deleteMaterialByName(long courseID, String materialName) {
        if (courseRepository.existsById(courseID)) {
            if (!getMaterialLinkByName(courseID, materialName).equals("No Course with this ID is found")) {
                courseRepository.save(getCourseByID(courseID).orElse(null).deleteMaterialByName(materialName));
                return materialName + " Deleted";
            } else {
                return "No Material with this name is found for this course";
            }
        }
        return "No Course with this ID is found";
    }
}
