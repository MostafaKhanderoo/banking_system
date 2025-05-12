package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.ApplicationContext;
import app.cfg.SessionFactoryInstance;
import app.entity.Boss;
import app.exception.NotFoundException;
import app.exception.PasswordWrongException;
import app.exception.UserNotFoundException;
import app.repository.BossRepository;
import app.repository.impl.BossRepositoryImpl;
import app.service.BossService;
import app.service.authentication.AuthenticationBoss;
import org.hibernate.Session;

public class BossServiceImpl extends BaseServiceImpl<Long, Boss, BossRepository>
        implements BossService {

    public BossServiceImpl(BossRepository repository) {
        super(repository);
    }

    BossRepositoryImpl bossRepository = new BossRepositoryImpl(Boss.class);

    @Override
    public Boss findByPersonnelCode(long personnelCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
               return bossRepository.findByPersonnelCode(session, personnelCode);
            } catch (UserNotFoundException e) {

                throw new UserNotFoundException("boss with this personnelCode " + personnelCode + " not found!");
            }
        }
    }

    @Override
    public void login(Long personnelCode, String password,int branch) {
        var boss = findByPersonnelCode(personnelCode);
        if (boss == null)
            throw new UserNotFoundException("boss with this personnelCode " + personnelCode + " not found!");

        else {
           if (!boss.getPassword().equals(password))
               throw new PasswordWrongException("Password is wrong!");
           else {
               var res =ApplicationContext.getBankService().findBankByBranch(branch);
               if (res == null || !res.getBoss().getPersonnelCode().equals(boss.getPersonnelCode()))
                    throw new NotFoundException("You do not have access to this branch.");

                    AuthenticationBoss.setLoggedInBoss(boss);
           }


        }

    }
}
