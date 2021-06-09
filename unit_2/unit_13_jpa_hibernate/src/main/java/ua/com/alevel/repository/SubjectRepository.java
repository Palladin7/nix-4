package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Subject;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubjectRepository {

    public Subject getSubjectById(Long id) {
        Subject subject = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subject = session.load(Subject.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);
            Root<Subject> root = cr.from(Subject.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            subjects = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public void addSubject(Subject subject) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
        }
    }

    public void updateSubject(Subject subject) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(subject);
            session.getTransaction().commit();
        }
    }

    public void deleteSubjectById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Subject subject = getSubjectById(id);
            session.beginTransaction();
            session.delete(subject);
            session.getTransaction().commit();
        }
    }
}
