package app.service;

import app.base.service.BaseService;
import app.entity.Employee;

import java.util.Optional;

public interface EmployeeService extends BaseService<Long, Employee> {

    Boolean personnelCodeExistence(Long personnelCode);
    void login (Long personnelCode, String password);
    Optional<Employee> findByPersonnelCode(Long personnelCode);
}
