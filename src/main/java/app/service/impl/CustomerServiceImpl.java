package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.entity.Customer;
import app.repository.impl.CustomerRepositoryImpl;
import app.service.CustomerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl extends BaseServiceImpl<Long , Customer , CustomerRepositoryImpl> implements CustomerService {


    public CustomerServiceImpl(CustomerRepositoryImpl repository) {
        super(repository);
    }

}
