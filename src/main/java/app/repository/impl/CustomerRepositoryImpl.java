package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Account;
import app.entity.CreditCard;
import app.entity.Customer;
import app.repository.CustomerRepository;
import org.hibernate.Session;

import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Long, Customer>
        implements CustomerRepository {


    public CustomerRepositoryImpl(Class<Customer> entity) {
        super(entity);
    }


    @Override
    public Customer createAccountAndCustomer(Session session, Account account, Customer customer) {
         session.persist(customer);
        session.persist(account);
        return customer;
    }

    @Override
    public Optional<Customer> findByNationalCode(Session session, String nationalCode) {
        return   session.createQuery
                        ("from Customer c where c.nationalCode = :nationalCode",Customer.class)
                .setParameter("nationalCode",nationalCode).uniqueResultOptional();
    }

    @Override
    public Long userNameCheck(Session session, String username) {
       return session.createQuery
                ("SELECT count(id) from Customer u where u.username= :username ",Long.class)
                       .setParameter("username",username).getSingleResult();

    }

    @Override
    public Long nationalCodeCheck(Session session, String nationalCode) {
        return session.createQuery
                ("select count(id) from Customer n where n.nationalCode=:nationalCode",Long.class)
                .setParameter("nationalCode",nationalCode).getSingleResult();
    }

    @Override
    public Long creditCardCheck(Session session, Customer customer, CreditCard creditCard) {
      return  session.createQuery("select  count(id) from Customer c where c.creditCard =:creditCard",Long.class)
                .setParameter("creditCard",creditCard).getSingleResult();
    }


    @Override
    public void setCreditCardForCustomer(Session session, String nationalCode, CreditCard creditCard) {
        session.createQuery("from  Customer c where c.nationalCode = :nationalCode ",Customer.class)
                .setParameter("nationalCode" ,nationalCode).getSingleResult();

    }
}
