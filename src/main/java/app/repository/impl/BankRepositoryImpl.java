package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Bank;
import app.entity.Boss;
import app.repository.BankRepository;
import org.hibernate.Session;

public class BankRepositoryImpl extends BaseRepositoryImpl<Long, Bank>
implements BankRepository {

    public BankRepositoryImpl(Class<Bank> entity) {
        super(entity);
    }

    @Override
    public Bank findBankByBranch(Session session, int branch) {
       return session.createQuery("from Bank b where b.branch = :branch ",Bank.class)
               .setParameter("branch" ,branch).uniqueResult();
    }

    @Override
    public Bank findBankByBossPersonnelCode(Session session, Long personnelCode) {
      return session.createQuery("from Bank b where b.boss.personnelCode = :personnelCode", Bank.class)
                .setParameter("personnelCode" ,personnelCode ).uniqueResult();
    }
}
