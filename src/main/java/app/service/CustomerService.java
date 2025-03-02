package app.service;

import app.base.service.BaseService;
import app.entity.Customer;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface CustomerService extends BaseService<Long , Customer> {
    @Override
    Customer save(Customer customer);

    @Override
    Customer update(Customer customer);

    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long id);

    @Override
    void deleteById(Long id);
}
