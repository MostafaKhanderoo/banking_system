package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Bank;
import org.hibernate.Session;

public interface BankRepository extends BaseRepository<Long, Bank> {
    Bank findBankByBranch(Session session , int branch);
    Bank findBankByBossPersonnelCode(Session session ,Long id);



}
