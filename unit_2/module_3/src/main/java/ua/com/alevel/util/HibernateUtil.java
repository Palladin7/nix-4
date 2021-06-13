package ua.com.alevel.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(String username, String password) {
        try {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.username", username)
                    .setProperty("hibernate.connection.password", password)
                    .configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
