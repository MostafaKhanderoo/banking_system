package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Account;
import app.entity.Bank;
import app.entity.Customer;
import org.hibernate.Session;

public interface AccountRepository extends BaseRepository<Long, Account> {

    Long checkCustomerAccount(Session session , String bankName, String nationalCode);
    Account findAccountByCustomer(Session session,String nationalCode);

    Account findAccountByBankNameAndCustomerLogged(Session session,String bankName,String nationalCode);

    Account findAccountByCreditCard(Session session,String cardNumber);

    void deposit (Session session,String address,Double amount);
    void withdraw (Session session,String address,Double amount);

}
