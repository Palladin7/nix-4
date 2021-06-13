package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransactionRepository {

    public Transaction getTransactionById(Long id) {
        Transaction transaction;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.load(Transaction.class, id);
        }

        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
            Root<Transaction> root = cq.from(Transaction.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            transactions = query.getResultList();
        }

        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(transaction);
            session.getTransaction().commit();
        }
    }
}
