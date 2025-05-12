package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.ApplicationContext;
import app.cfg.SessionFactoryInstance;
import app.entity.AccountTransaction;
import app.entity.Transaction;
import app.exception.*;
import app.repository.TransactionRepository;
import app.repository.impl.TransactionRepositoryImpl;
import app.service.TransactionService;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl extends BaseServiceImpl<Long, Transaction, TransactionRepository>
implements TransactionService {
    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }

    TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl(Transaction.class);
    @Override
    public Double inventory(String cardNumber, int month,int year,String cvv, String password) {
     var creditCard=  ApplicationContext.getCreditCardService().findCardByCardNumber(cardNumber);

     if (!creditCard.getAccount().isAccountLock() == true)
         throw new AccountLockException("Your account has been blocked.");

     if (!creditCard.getPassword().equals(password))
        throw new PasswordWrongException();

     int cardMonth=creditCard.getDataCard().getMonthValue();
     int cardYear= creditCard.getDataCard().getYear() % 100;

     String cardCvv = creditCard.getCVV2();
     if (cardMonth != month || cardYear != year|| !cardCvv.equals(cvv))
             throw new CardInformationIncorrectException();

      var accountCreditCard=  ApplicationContext.getAccountService().findAccountByCreditCard(cardNumber);

      if (accountCreditCard.getBalance() > 500) {
         ApplicationContext.getAccountService()
                 .deposit(accountCreditCard.getAddress() , 200d);
          return accountCreditCard.getBalance();
      }
      return accountCreditCard.getBalance();
    }

    @Override
    public Transaction transactMoney(String withdrawCardNumber, String depositorCardNumber
            , Double amount, int month, int year, String cvv, String password) {

        var transferCard=  ApplicationContext.getCreditCardService().findCardByCardNumber(withdrawCardNumber);

        var depositCard=  ApplicationContext.getCreditCardService().findCardByCardNumber(depositorCardNumber);


        if (!transferCard.getAccount().isAccountLock())
            throw new AccountLockException("Your account has been blocked.");

        if (!depositCard.getAccount().isAccountLock())
            throw new AccountLockException("deposit account has been blocked.");

        if (!transferCard.getPassword().equals(password))
            throw new PasswordWrongException();

        int cardMonth=transferCard.getDataCard().getMonthValue();
        int cardYear= transferCard.getDataCard().getYear() % 100;

        String cardCvv = transferCard.getCVV2();
        if (cardMonth != month || cardYear != year|| !cardCvv.equals(cvv))
            throw new CardInformationIncorrectException();
     var withdrawAccount=   ApplicationContext.getAccountService().findAccountByCreditCard(withdrawCardNumber);
     var depositorAccount=   ApplicationContext.getAccountService().findAccountByCreditCard(depositorCardNumber);
    if (withdrawAccount.getBalance() < amount)
        throw new LowBalanceException("Insufficient account balance");

    ApplicationContext.getAccountService().withdraw(withdrawAccount.getAddress(),amount);
        ApplicationContext.getAccountService().deposit(depositorAccount.getAddress(),amount);


        Transaction transaction = new Transaction();
    transaction.setTimeTransaction(LocalDateTime.now());
    transaction.setWithdrawAccount(withdrawCardNumber);
    transaction.setDepositorAccount(depositorCardNumber);
    transaction.setAmount(amount);
    save(transaction);
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setTransaction(transaction);
        accountTransaction.setAccount(withdrawAccount);
        ApplicationContext.getAccountTransactionService().save(accountTransaction);
        return transaction;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(String cardNumber,String password) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            try{

             var account=  ApplicationContext.getAccountService().findAccountByCreditCard(cardNumber);
             var creditCard = ApplicationContext.getCreditCardService().findCardByCardNumber(cardNumber);
             if (!creditCard.getPassword().equals(password))
                 throw new PasswordWrongException("card password is wrong try again");

             var transactions= transactionRepository.findTransactionsByAccount(session,account);
             if (transactions ==null)
                 throw new NotFoundException("no transaction found for this creditCard");

//
//                for (Transaction t :
//                        transactions) {
//                    System.out.println( t.getTimeTransaction());
//                    t.getTimeTransaction();
//                    t.getWithdrawAccount();
//                    t.getDepositorAccount();
//                }
             return transactions;
            }catch (NotFoundException e){
                throw new NotFoundException("not found Transactions for this account!");
            }
        }
    }


}
