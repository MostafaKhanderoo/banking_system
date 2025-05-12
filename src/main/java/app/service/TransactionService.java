package app.service;

import app.base.service.BaseService;
import app.entity.Transaction;

import java.util.List;

public interface TransactionService extends BaseService<Long, Transaction> {

    Double inventory(String cardNumber, int month,int year,String cvv, String password);
    Transaction transactMoney(String withdrawCardNumber, String depositorCardNumber, Double amount,
                              int month,int year,String cvv, String password);

    List<Transaction> findTransactionsByAccount( String cardNumber,String password);

}
