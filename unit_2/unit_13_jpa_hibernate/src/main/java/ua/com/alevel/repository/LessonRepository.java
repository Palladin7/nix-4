package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Lesson;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LessonRepository {

    public Lesson getLessonById(Long id) {
        Lesson lesson = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            lesson = session.load(Lesson.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lesson;
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Lesson> cr = cb.createQuery(Lesson.class);
            Root<Lesson> root = cr.from(Lesson.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            lessons = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public void addLesson(Lesson lesson) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(lesson);
            session.getTransaction().commit();
        }
    }

    public void updateLesson(Lesson lesson) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(lesson);
            session.getTransaction().commit();
        }
    }

    public void deleteLessonById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Lesson lesson = getLessonById(id);
            session.beginTransaction();
            session.delete(lesson);
            session.getTransaction().commit();
        }
    }
}
