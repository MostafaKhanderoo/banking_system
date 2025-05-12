package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Account;
import app.entity.Customer;
import app.repository.AccountRepository;
import org.hibernate.Session;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Long, Account>
implements AccountRepository {

    public AccountRepositoryImpl(Class<Account> entity) {
        super(entity);
    }

    @Override
    public Long checkCustomerAccount(Session session, String bankName, String nationalCode) {
     return  session.createQuery("select count (id) from Account a where a.bank.name =:bankName" +
                " and a.customer.nationalCode = :nationalCode ",Long.class)
                .setParameter("bankName",bankName).setParameter("nationalCode",nationalCode)
                .getSingleResult();
    }

    @Override
    public Account findAccountByCustomer(Session session, String nationalCode) {
      return   session.createQuery(" from Account n where n.customer.nationalCode = :nationalCode",
                Account.class).setParameter("nationalCode",nationalCode).uniqueResult();
    }

    @Override
    public Account findAccountByBankNameAndCustomerLogged(Session session, String bankName, String nationalCode) {
       return   session.createQuery("from Account c where c.bank.name =:bankName" +
                 " and c.customer.nationalCode = :nationalCode",Account.class).setParameter("bankName",bankName)
                 .setParameter("nationalCode",nationalCode).uniqueResult();
    }

    @Override
    public Account findAccountByCreditCard(Session session, String cardNumber) {
      return   session.createQuery("select c.account from CreditCard c where c.cardNumber =:cardNumber"
                ,Account.class).setParameter("cardNumber",cardNumber).uniqueResult();
    }

    @Override
    public void deposit(Session session, String address, Double amount) {
     var account =   session.createQuery("from Account a where a.address =:address",Account.class)
                .setParameter("address",address).uniqueResult();
     account.setBalance(account.getBalance() + amount);

    }

    @Override
    public void withdraw(Session session, String address, Double amount) {
        var account =   session.createQuery("from Account a where a.address =:address",Account.class)
                .setParameter("address",address).uniqueResult();
        account.setBalance(account.getBalance() - amount);

    }

}
