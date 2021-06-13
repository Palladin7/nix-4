package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository {

    public User getUserById(Long id) {
        User user;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.load(User.class, id);
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            users = query.getResultList();
        }

        return users;
    }

    public void addUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = getUserById(id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
