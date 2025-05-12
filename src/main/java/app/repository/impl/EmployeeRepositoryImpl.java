package app.repository.impl;

import app.base.repository.BaseRepositoryImpl;
import app.entity.Employee;
import app.repository.EmployeeRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Long, Employee>
implements EmployeeRepository {

    public EmployeeRepositoryImpl(Class<Employee> entity) {
        super(entity);
    }


    @Override
    public Boolean personnelCodeExistence(Session session, Long personnelCode) {
        Employee employee = null;
        try {
            employee = session
                    .createQuery("FROM Employee a WHERE a.personnelCode = :personnelCode", Employee.class)
                    .setParameter("personnelCode", personnelCode)
                    .getSingleResult();
            return employee != null;
        } catch (NoResultException e) {
            return employee ==null;
        }
    }


    @Override
    public Optional<Employee> findByPersonnelCode(Session session, Long personnelCode) {
      return  session.createQuery("from Employee a where a.personnelCode = :personnelCode",Employee.class)
                .setParameter("personnelCode",personnelCode).uniqueResultOptional();

    }
}
