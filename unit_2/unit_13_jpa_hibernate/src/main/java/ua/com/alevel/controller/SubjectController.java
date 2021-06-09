package ua.com.alevel.controller;

import ua.com.alevel.entity.Subject;
import ua.com.alevel.service.SubjectService;

import java.util.List;

public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController() {
        this.subjectService = new SubjectService();
    }

    public Subject getGroupById(Long id) {
        return subjectService.getSubjectById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    public void addSubject(Subject subject) {
        subjectService.addSubject(subject);
    }

    public void updateSubject(Subject subject) {
        subjectService.updateSubject(subject);
    }

    public void deleteSubjectById(Long id) {
        subjectService.deleteSubjectById(id);
    }
}
