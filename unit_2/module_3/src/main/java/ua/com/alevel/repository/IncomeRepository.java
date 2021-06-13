package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Income;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class IncomeRepository {

    public Income getIncomeByType(String type) {
        Income income = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            income = session.load(Income.class, type);
        }

        return income;
    }

    public List<Income> getAllIncomes() {
        List<Income> incomes = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Income> cq = cb.createQuery(Income.class);
            Root<Income> root = cq.from(Income.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            incomes = query.getResultList();
        }

        return incomes;
    }

    public void addIncome(Income income) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(income);
            session.getTransaction().commit();
        }
    }

    public void updateIncome(Income income) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(income);
            session.getTransaction().commit();
        }
    }

    public void deleteIncomeByType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Income income = getIncomeByType(type);
            session.beginTransaction();
            session.delete(income);
            session.getTransaction().commit();
        }
    }
}
