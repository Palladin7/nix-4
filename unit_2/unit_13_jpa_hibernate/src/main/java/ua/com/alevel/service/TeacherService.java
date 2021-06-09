package ua.com.alevel.service;

import ua.com.alevel.entity.Teacher;
import ua.com.alevel.repository.TeacherRepository;

import java.util.List;

public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService() {
        this.teacherRepository = new TeacherRepository();
    }

    public Teacher getTeacherById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return teacherRepository.getTeacherById(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.addTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
    }

    public void deleteTeacherById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        teacherRepository.deleteTeacherById(id);
    }

    public String getTheMostSuccessfulGroupForTeacher(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return teacherRepository.getTheMostSuccessfulGroupForTeacher(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllTeachers().size();
    }
}
