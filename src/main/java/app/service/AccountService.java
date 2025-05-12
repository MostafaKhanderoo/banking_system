package app.service;

import app.base.service.BaseService;
import app.entity.Account;

public interface AccountService extends BaseService<Long, Account> {
    void checkCustomerAccountUniq(String bankName,String nationalCode);
    Account createAccount(String customerNationalCode);
    Account findAccountByCustomer(String nationalCode);
    Account findAccountByBankNameAndCustomerLogged(String bankName,String nationalCode);
    Account findAccountByCreditCard(String cardNumber);
    Account findAccountByCreditCardAndPassword(String cardNumber,String password);
    void deposit(String address, Double amount);
    void withdraw ( String address, Double amount);


}
