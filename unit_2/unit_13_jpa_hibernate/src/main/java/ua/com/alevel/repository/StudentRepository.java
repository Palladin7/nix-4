package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.*;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class StudentRepository {

    public Student getStudentById(Long id) {
        Student student = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            student = session.load(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> cr = cb.createQuery(Student.class);
            Root<Student> root = cr.from(Student.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            students = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
    }

    public void updateStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        }
    }

    public void deleteStudentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = getStudentById(id);
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        }
    }

    public String getNextLessonForStudent(Long id) {
        StringBuilder stringBuilder = new StringBuilder("");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.load(Student.class, id);
            Group group = student.getGroup();
            Teacher teacher = group.getTeacher();
            Course course = group.getCourse();
            List<Lesson> lessons = course.getLessons();

            LocalDateTime now = LocalDateTime.now();
            Lesson nextLesson = lessons.stream()
                    .filter(e -> e.getDate().compareTo(now) > 0)
                    .sorted(Comparator.comparing(Lesson::getDate))
                    .findFirst().get();

            stringBuilder.append(nextLesson.getDate());
            stringBuilder.append(" ");
            stringBuilder.append(teacher.getName());
            stringBuilder.append(" ");
            stringBuilder.append(nextLesson.getCourse().getName());
            stringBuilder.append(" ");
            stringBuilder.append(nextLesson.getSubject().getName());
        }

        return stringBuilder.toString();
    }
}
