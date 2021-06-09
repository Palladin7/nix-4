package ua.com.alevel.controller;

import ua.com.alevel.entity.Lesson;
import ua.com.alevel.service.LessonService;

import java.util.List;

public class LessonController {

    private final LessonService lessonService;

    public LessonController() {
        this.lessonService = new LessonService();
    }

    public Lesson getLessonById(Long id) {
        return lessonService.getLessonById(id);
    }

    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    public void addLesson(Lesson lesson) {
        lessonService.addLesson(lesson);
    }

    public void updateLesson(Lesson lesson) {
        lessonService.updateLesson(lesson);
    }

    public void deleteLessonById(Long id) {
        lessonService.deleteLessonById(id);
    }
}
