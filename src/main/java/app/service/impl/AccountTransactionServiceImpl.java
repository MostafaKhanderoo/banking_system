package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.entity.AccountTransaction;
import app.repository.AccountTransactionRepository;
import app.service.AccountTransactionService;

public class AccountTransactionServiceImpl extends
        BaseServiceImpl<Long, AccountTransaction, AccountTransactionRepository>
        implements AccountTransactionService {

    public AccountTransactionServiceImpl(AccountTransactionRepository repository) {
        super(repository);
    }
}
