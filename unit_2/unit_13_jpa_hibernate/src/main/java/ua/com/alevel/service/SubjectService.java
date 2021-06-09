package ua.com.alevel.service;

import ua.com.alevel.entity.Subject;
import ua.com.alevel.repository.SubjectRepository;

import java.util.List;

public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService() {
        this.subjectRepository = new SubjectRepository();
    }

    public Subject getSubjectById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return subjectRepository.getSubjectById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.getAllSubjects();
    }

    public void addSubject(Subject subject) {
        subjectRepository.addSubject(subject);
    }

    public void updateSubject(Subject subject) {
        subjectRepository.updateSubject(subject);
    }

    public void deleteSubjectById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        subjectRepository.deleteSubjectById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllSubjects().size();
    }
}
