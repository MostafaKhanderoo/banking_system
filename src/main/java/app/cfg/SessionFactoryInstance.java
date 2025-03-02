package app.cfg;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    public final static SessionFactory sessionFactory;


    static {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

}
