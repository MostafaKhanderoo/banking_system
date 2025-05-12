package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Account;
import app.entity.AccountTransaction;
import app.entity.Transaction;
import org.hibernate.Session;

import java.util.List;

public interface TransactionRepository extends BaseRepository<Long, Transaction> {

    Transaction transactMoney(Session session, String withdrawCardNumber, String depositorCardNumber, Double amount);


    Double inventory(Session session, String bankName, String accountAddress);


    List<Transaction> findTransactionsByAccount(Session session, Account account);


}
