package app.service;

import app.base.service.BaseService;
import app.entity.Boss;

public interface BossService extends BaseService<Long, Boss> {
    Boss findByPersonnelCode (long personnelCode);
    void login(Long personnelCode ,String password,int branch);
}
