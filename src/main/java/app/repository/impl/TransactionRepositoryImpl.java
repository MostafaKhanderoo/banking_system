package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Account;
import app.entity.Transaction;
import app.repository.TransactionRepository;
import org.hibernate.Session;

import java.util.List;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Long, Transaction>
implements TransactionRepository {
    public TransactionRepositoryImpl(Class<Transaction> entity) {
        super(entity);
    }

    @Override
    public Transaction transactMoney(Session session, String withdrawCardNumber, String depositorCardNumber, Double amount) {
       return  null;
    }

    @Override
    public Double inventory(Session session, String bankName, String accountAddress) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(Session session, Account account) {
      return   session.createQuery
                ("select c.transaction from " +
                        " AccountTransaction c where c.account = :account",Transaction.class)
                .setParameter("account",account).getResultList();
    }


}
