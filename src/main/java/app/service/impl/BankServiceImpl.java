package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.SessionFactoryInstance;
import app.entity.Bank;
import app.exception.NotFoundException;
import app.exception.UserNotFoundException;
import app.repository.BankRepository;
import app.repository.impl.BankRepositoryImpl;
import app.service.BankService;
import app.service.authentication.AuthenticationBoss;
import org.hibernate.Session;

public class BankServiceImpl extends BaseServiceImpl<Long, Bank , BankRepository>
        implements BankService {
    public BankServiceImpl(BankRepository repository) {
        super(repository);
    }

    BankRepositoryImpl bankRepository = new BankRepositoryImpl(Bank.class);

    @Override
    public Bank findBankByBranch(int branch) {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                return bankRepository.findBankByBranch(session, branch);

            } catch (NotFoundException e) {
                throw new NotFoundException("Bank with branch " + branch + " not found!");
            }
        }
    }

    @Override
    public Bank findBankByBossPersonnelCode() {
        try(Session session = SessionFactoryInstance.sessionFactory.openSession()){
            try {
              var personnelCode=  AuthenticationBoss.getLoggedInBoss().getPersonnelCode();
                return  bankRepository.findBankByBossPersonnelCode(session, personnelCode);

            }catch (UserNotFoundException e){

                throw new UserNotFoundException("No bosses login application");
            }
        }
    }
}
