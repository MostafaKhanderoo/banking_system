package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Boss;
import org.hibernate.Session;

public interface BossRepository extends BaseRepository<Long, Boss> {
    Boss findByPersonnelCode (Session session,Long personnelCode);
}
