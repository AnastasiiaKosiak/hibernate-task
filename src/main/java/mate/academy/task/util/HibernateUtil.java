package mate.academy.task.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception exception) {
            throw new RuntimeException("Error creating a Session Factory", exception);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
