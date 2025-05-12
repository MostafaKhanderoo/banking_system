package app.repository;

import app.base.repository.BaseRepository;
import app.entity.Employee;
import org.hibernate.Session;

import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Long, Employee> {

    Boolean personnelCodeExistence(Session session, Long personnelCode);
    Optional<Employee> findByPersonnelCode(Session session , Long personnelCode);
}
