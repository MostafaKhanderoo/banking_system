package app.repository;

import app.base.repository.BaseRepository;
import app.base.repository.BaseRepositoryImpl;
import app.entity.Customer;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Long, Customer> {
    @Override
    Customer save(Session session, Customer customer);

    @Override
    Customer update(Session session, Customer customer);

    @Override
    Optional<Customer> findById(Session session, Long id);

    @Override
    List<Customer> findAll(Session session);

    @Override
    void deleteById(Session session, Long id);
}
