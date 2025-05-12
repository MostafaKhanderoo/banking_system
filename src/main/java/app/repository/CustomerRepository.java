package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Account;
import app.entity.CreditCard;
import app.entity.Customer;
import org.hibernate.Session;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Long, Customer> {

    Customer createAccountAndCustomer(Session session,Account account, Customer customer);

    Optional<Customer> findByNationalCode(Session session ,String nationalCode);

    Long userNameCheck(Session session, String username);

    Long nationalCodeCheck(Session session ,String nationalCode);

    Long creditCardCheck(Session session ,Customer customer, CreditCard creditCard);

    void setCreditCardForCustomer(Session session, String nationalCode, CreditCard creditCard);

}
