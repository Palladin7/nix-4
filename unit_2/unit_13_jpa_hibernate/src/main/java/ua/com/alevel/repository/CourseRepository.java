package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Course;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseRepository {

    public Course getCourseById(Long id) {
        Course course = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            course = session.load(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return course;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Course> cr = cb.createQuery(Course.class);
            Root<Course> root = cr.from(Course.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public void addCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        }
    }

    public void updateCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        }
    }

    public void deleteCourseById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Course course = getCourseById(id);
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
        }
    }
}
