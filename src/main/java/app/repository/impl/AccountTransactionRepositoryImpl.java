package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.AccountTransaction;
import app.repository.AccountTransactionRepository;

public class AccountTransactionRepositoryImpl extends BaseRepositoryImpl<Long, AccountTransaction>
implements AccountTransactionRepository {
    public AccountTransactionRepositoryImpl(Class<AccountTransaction> entity) {
        super(entity);
    }


}
