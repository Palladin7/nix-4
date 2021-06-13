package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Account;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountRepository {

    public Account getAccountById(Long id) {
        Account account;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            account = session.load(Account.class, id);
        }

        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Account> cq = cb.createQuery(Account.class);
            Root<Account> root = cq.from(Account.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            accounts = query.getResultList();
        }

        return accounts;
    }

    public void addAccount(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
        }
    }

    public void updateAccount(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
        }
    }

    public void deleteAccountById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account = getAccountById(id);
            session.beginTransaction();
            session.delete(account);
            session.getTransaction().commit();
        }
    }
}
