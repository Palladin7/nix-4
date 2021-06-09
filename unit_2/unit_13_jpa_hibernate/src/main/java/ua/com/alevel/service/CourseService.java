package ua.com.alevel.service;

import ua.com.alevel.entity.Course;
import ua.com.alevel.repository.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    public Course getCourseById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return courseRepository.getCourseById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    public void deleteCourseById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        courseRepository.deleteCourseById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllCourses().size();
    }
}
