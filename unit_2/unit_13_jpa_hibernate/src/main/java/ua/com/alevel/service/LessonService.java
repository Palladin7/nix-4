package ua.com.alevel.service;

import ua.com.alevel.entity.Lesson;
import ua.com.alevel.repository.LessonRepository;

import java.util.List;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService() {
        this.lessonRepository = new LessonRepository();
    }

    public Lesson getLessonById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return lessonRepository.getLessonById(id);
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    public void addLesson(Lesson lesson) {
        lessonRepository.addLesson(lesson);
    }

    public void updateLesson(Lesson lesson) {
        lessonRepository.updateLesson(lesson);
    }

    public void deleteLessonById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        lessonRepository.deleteLessonById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllLessons().size();
    }
}
