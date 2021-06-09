package ua.com.alevel.controller;

import ua.com.alevel.entity.Course;
import ua.com.alevel.service.CourseService;

import java.util.List;

public class CourseController {

    private final CourseService courseService;

    public CourseController() {
        this.courseService = new CourseService();
    }

    public Course getCourseById(Long id) {
        return courseService.getCourseById(id);
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public void addCourse(Course course) {
        courseService.addCourse(course);
    }

    public void updateCourse(Course course) {
        courseService.updateCourse(course);
    }

    public void deleteCourseById(Long id) {
        courseService.deleteCourseById(id);
    }
}
