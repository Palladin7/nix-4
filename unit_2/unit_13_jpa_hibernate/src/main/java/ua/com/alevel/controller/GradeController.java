package ua.com.alevel.controller;

import ua.com.alevel.entity.Grade;
import ua.com.alevel.service.GradeService;

import java.util.List;

public class GradeController {

    private final GradeService gradeService;

    public GradeController() {
        this.gradeService = new GradeService();
    }

    public Grade getGradeById(Long id) {
        return gradeService.getGradeById(id);
    }

    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
    }

    public void updateGrade(Grade grade) {
        gradeService.updateGrade(grade);
    }

    public void deleteGradeById(Long id) {
        gradeService.deleteGradeById(id);
    }
}
