package app.service;

import app.base.service.BaseService;
import app.entity.Bank;

public interface BankService extends BaseService<Long, Bank> {

    Bank findBankByBranch(int branch);
    Bank findBankByBossPersonnelCode();
}
