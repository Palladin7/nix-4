package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Expense;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExpenseRepository {

    public Expense getExpenseByType(String type) {
        Expense expense = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            expense = session.load(Expense.class, type);
        }

        return expense;
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
            Root<Expense> root = cq.from(Expense.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            expenses = query.getResultList();
        }

        return expenses;
    }

    public void addExpense(Expense expense) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(expense);
            session.getTransaction().commit();
        }
    }

    public void updateExpense(Expense expense) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(expense);
            session.getTransaction().commit();
        }
    }

    public void deleteExpenseByType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Expense expense = getExpenseByType(type);
            session.beginTransaction();
            session.delete(expense);
            session.getTransaction().commit();
        }
    }
}
