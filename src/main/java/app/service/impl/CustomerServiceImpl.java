package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.SessionFactoryInstance;
import app.entity.CreditCard;
import app.entity.Customer;
import app.exception.CreditCardAlreadyExistsException;
import app.exception.NationalCodeExistsException;
import app.exception.UserAlreadyExistsException;
import app.exception.UserNotFoundException;
import app.repository.CustomerRepository;
import app.repository.impl.CustomerRepositoryImpl;
import app.service.CustomerService;
import org.hibernate.Session;

import java.util.Optional;

public class CustomerServiceImpl extends BaseServiceImpl<Long , Customer , CustomerRepository>
        implements CustomerService {

    CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(Customer.class);
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }



    @Override
    public Customer save(Customer customer) {
        try {
//            var employeeBank= AuthenticationEmployee.getLoggedInEmployee().getBank();
//            Account account = new Account();
//            account.setBalance(0d);
//            account.setCustomer(customer);
//            account.setBank(employeeBank);
//            account.setAccountLock(true);
         nationalCodeCheck(customer.getNationalCode());
            userNameCheck(customer.getUsername());
                super.save(customer);
//             account.setAddress(RandomStringGenerator.generateRandomString());
//             ApplicationContext.getAccountService().save(account);
         return customer;
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException("A user with these characteristics already exists.");
        }

    }


    @Override
    public Optional<Customer> findByNationalCode(String nationalCode) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            session.beginTransaction();
            try {
               var resultCustomer=  customerRepository.
                       findByNationalCode(session,nationalCode);

                session.getTransaction().commit();
                return resultCustomer;
            }catch (UserNotFoundException e){
                throw new UserNotFoundException
                        ("Customer with this NationalCode "+nationalCode+" not found!");
            }
        }
    }

    @Override
    public void userNameCheck(String username) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            Long usernameUniq = customerRepository.userNameCheck(session, username);
            if (usernameUniq != null && usernameUniq > 0)
                throw new UserAlreadyExistsException();
        }
    }

    @Override
    public void nationalCodeCheck(String nationalCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            Long nationalCodeUniq = customerRepository.nationalCodeCheck(session, nationalCode);
            if (nationalCodeUniq != null && nationalCodeUniq > 0)
                throw new NationalCodeExistsException();

        }
    }

    @Override
    public void creditCardCheck(Customer customer, CreditCard creditCard) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            Long creditCardUniq = customerRepository.creditCardCheck(session, customer,creditCard);
            if (creditCardUniq != null && creditCardUniq > 0)
                throw new NationalCodeExistsException();

        }
    }

    @Override
    public void addCreditCardForCustomer(String nationalCode, CreditCard creditCard) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            session.beginTransaction();
            try {
                customerRepository.setCreditCardForCustomer(session,nationalCode,creditCard);
                session.getTransaction().commit();
            }catch (CreditCardAlreadyExistsException e){
                throw new CreditCardAlreadyExistsException();
            }
        }
    }
}