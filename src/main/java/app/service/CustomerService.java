package app.service;

import app.base.service.BaseService;
import app.entity.CreditCard;
import app.entity.Customer;

import java.util.Optional;

public interface CustomerService extends BaseService<Long , Customer> {
Optional<Customer> findByNationalCode(String nationalCode);

    void userNameCheck(String username);

    void nationalCodeCheck(String nationalCode);

    void creditCardCheck(Customer customer, CreditCard creditCard);
    void addCreditCardForCustomer(String nationalCode, CreditCard creditCard);

}
