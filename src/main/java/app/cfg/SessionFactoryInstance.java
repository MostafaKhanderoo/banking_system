package app.cfg;

import app.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    public final static SessionFactory sessionFactory;


    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Bank.class)
                .addAnnotatedClass(Boss.class)
                .addAnnotatedClass(CreditCard.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Transaction.class)
                .addAnnotatedClass(AccountTransaction.class)
                .buildSessionFactory();
    }

}
