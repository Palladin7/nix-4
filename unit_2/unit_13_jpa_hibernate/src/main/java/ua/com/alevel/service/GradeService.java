package ua.com.alevel.service;

import ua.com.alevel.entity.Grade;
import ua.com.alevel.repository.GradeRepository;

import java.util.List;

public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService() {
        this.gradeRepository = new GradeRepository();
    }

    public Grade getGradeById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return gradeRepository.getGradeById(id);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }

    public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
    }

    public void updateGrade(Grade grade) {
        gradeRepository.updateGrade(grade);
    }

    public void deleteGradeById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        gradeRepository.deleteGradeById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllGrades().size();
    }
}
