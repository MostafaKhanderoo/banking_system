package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Customer;
import app.repository.CustomerRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Long, Customer> {


    public CustomerRepositoryImpl(Class<Customer> entity) {
        super(entity);
    }

    @Override
    public List<Customer> findAll(Session session) {
        return super.findAll(session);
    }

    @Override
    public Customer save(Session session, Customer customer) {
        session.persist(customer);
        return customer;
    }

    @Override
    public Customer update(Session session, Customer customer) {
        session.persist(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Session session, Long id) {
    return session.byId(Customer.class).loadOptional(id);
    }



    @Override
    public void deleteById(Session session, Long id) {
        super.deleteById(session, id);
    }
}
