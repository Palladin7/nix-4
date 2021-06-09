package ua.com.alevel.controller;

import ua.com.alevel.entity.Teacher;
import ua.com.alevel.service.TeacherService;

import java.util.List;

public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController() {
        this.teacherService = new TeacherService();
    }

    public Teacher getTeacherById(Long id) {
        return teacherService.getTeacherById(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    public void addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

    public void deleteTeacherById(Long id) {
        teacherService.deleteTeacherById(id);
    }

    public String getTheMostSuccessfulGroupForTeacher(Long id) {
        return teacherService.getTheMostSuccessfulGroupForTeacher(id);
    }
}
