package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Boss;
import app.repository.BossRepository;
import org.hibernate.Session;

public class BossRepositoryImpl extends BaseRepositoryImpl<Long, Boss>
implements BossRepository {
    public BossRepositoryImpl(Class<Boss> entity) {
        super(entity);
    }


    @Override
    public Boss findByPersonnelCode(Session session, Long personnelCode) {
        return session.createQuery("from  Boss a where  a.personnelCode = :personnelCode",Boss.class)
                .setParameter("personnelCode",personnelCode).uniqueResult();




    }
}
