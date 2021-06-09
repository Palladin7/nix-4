package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Grade;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GradeRepository {

    public Grade getGradeById(Long id) {
        Grade grade = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            grade = session.load(Grade.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grade;
    }

    public List<Grade> getAllGrades() {
        List<Grade> grades = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Grade> cr = cb.createQuery(Grade.class);
            Root<Grade> root = cr.from(Grade.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            grades = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grades;
    }

    public void addGrade(Grade grade) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(grade);
            session.getTransaction().commit();
        }
    }

    public void updateGrade(Grade grade) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(grade);
            session.getTransaction().commit();
        }
    }

    public void deleteGradeById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Grade grade = getGradeById(id);
            session.beginTransaction();
            session.delete(grade);
            session.getTransaction().commit();
        }
    }
}
