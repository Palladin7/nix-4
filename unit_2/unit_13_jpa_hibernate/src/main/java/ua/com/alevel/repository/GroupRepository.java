package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.Group;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GroupRepository {

    public Group getGroupById(Long id) {
        Group group = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            group = session.load(Group.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return group;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Group> cr = cb.createQuery(Group.class);
            Root<Group> root = cr.from(Group.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            groups = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groups;
    }

    public void addGroup(Group group) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(group);
            session.getTransaction().commit();
        }
    }

    public void updateGroup(Group group) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(group);
            session.getTransaction().commit();
        }
    }

    public void deleteGroupById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Group group = getGroupById(id);
            session.beginTransaction();
            session.delete(group);
            session.getTransaction().commit();
        }
    }
}
