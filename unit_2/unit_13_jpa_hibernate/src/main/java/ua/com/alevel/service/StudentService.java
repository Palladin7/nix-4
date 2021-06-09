package ua.com.alevel.service;

import ua.com.alevel.entity.Student;
import ua.com.alevel.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public Student getStudentById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return studentRepository.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public void deleteStudentById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        studentRepository.deleteStudentById(id);
    }

    public String getNextLessonForStudent(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return studentRepository.getNextLessonForStudent(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllStudents().size();
    }
}
