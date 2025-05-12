package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.ApplicationContext;
import app.cfg.SessionFactoryInstance;
import app.entity.Account;
import app.exception.*;
import app.repository.AccountRepository;
import app.repository.impl.AccountRepositoryImpl;
import app.service.AccountService;
import app.service.authentication.AuthenticationEmployee;
import org.hibernate.Session;

public class AccountServiceImpl extends BaseServiceImpl<Long, Account, AccountRepository>
        implements AccountService {
    AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(Account.class);

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public Account findAccountByCustomer(String nationalCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                var accountRes = accountRepository.findAccountByCustomer(session, nationalCode);
                session.getTransaction().commit();
                return accountRes;
            } catch (NotFoundException e) {
                session.getTransaction().rollback();
                throw new NotFoundException("Account with this nationalCode " + nationalCode + " not found");
            }
        }
    }

    @Override
    public Account findAccountByBankNameAndCustomerLogged(String bankName, String nationalCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                var account = accountRepository.findAccountByBankNameAndCustomerLogged(session, bankName, nationalCode);
                if (account == null)
                    throw new NotFoundException("bank with name " + bankName + " not found!");


                return account;
            } catch (NotFoundException e) {
                throw new NotFoundException("You do not have an account in this bank.");
            }
        }
    }

    @Override
    public Account findAccountByCreditCard(String cardNumber) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                var account = accountRepository.findAccountByCreditCard(session, cardNumber);
                if (account == null)
                    throw new NotFoundException();

                return account;
            } catch (NotFoundException e) {
                throw new NotFoundException("card with number " + cardNumber + " notFound");
            }
        }
    }

    @Override
    public Account findAccountByCreditCardAndPassword(String cardNumber, String password) {
        return null;
    }


    @Override
    public void checkCustomerAccountUniq(String bankName, String nationalCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            Long countAccountCheck = accountRepository
                    .checkCustomerAccount(session, bankName, nationalCode);
            if (countAccountCheck != null && countAccountCheck > 0)
                throw new CustomerHasAccountInThisBankException
                        ("Customer with nationalCode " + nationalCode + " has account in this bank");
        }
    }

    @Override
    public Account createAccount(String customerNationalCode) {
        Account account = new Account();
        var employeeBank = AuthenticationEmployee.getLoggedInEmployee().getBank();
        var customer = ApplicationContext.getCustomerService()
                .findByNationalCode(customerNationalCode)
                .orElseThrow(UserNotFoundException::new);

        checkCustomerAccountUniq(employeeBank.getName(), customer.getNationalCode());
        account.setAddress(RandomStringGenerator.generateRandomString());
        account.setBalance(0d);
        account.setCustomer(customer);
        account.setBank(employeeBank);
        account.setAccountLock(true);
//        CreditCard creditCard = new CreditCard();
//        ApplicationContext.getCreditCardService().save();
        save(account);
        return account;
    }

    @Override
    public void deposit(String address, Double amount) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
              accountRepository.deposit(session,address,amount);
              session.getTransaction().commit();
            } catch (ProblemAccountException e) {
                session.getTransaction().rollback();
                throw new PasswordWrongException("problem in deposit new balance in Account try again");
            }
        }

    }

    @Override
    public void withdraw( String address, Double amount) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                accountRepository.withdraw(session,address,amount);
                session.getTransaction().commit();
            } catch (ProblemAccountException e) {
                session.getTransaction().rollback();
                throw new PasswordWrongException("problem in withdraw balance in Account try again");
            }
        }
    }
}
