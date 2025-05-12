package app.service.impl;

import app.base.service.BaseServiceImpl;
import app.cfg.ApplicationContext;
import app.cfg.SessionFactoryInstance;
import app.entity.Employee;
import app.exception.UserNotFoundException;
import app.repository.EmployeeRepository;
import app.repository.impl.EmployeeRepositoryImpl;
import app.service.EmployeeService;
import app.service.authentication.AuthenticationEmployee;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

public class EmployeeServiceImpl extends BaseServiceImpl<Long, Employee, EmployeeRepository>
        implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(Employee.class);


    @Override
    public Boolean personnelCodeExistence(Long personnelCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                boolean exists = employeeRepository.personnelCodeExistence(session, personnelCode);
                session.getTransaction().commit();
                return exists;

            } catch (NoResultException e) {
                return false;

            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException("An unexpected error ", e);
            }

        }
    }


    @Override
    public void login(Long personnelCode, String password) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                if (employeeRepository.personnelCodeExistence(session, personnelCode) !=null) {
                    Employee employee = employeeRepository.findByPersonnelCode(session, personnelCode).
                            orElseThrow(() -> new UserNotFoundException
                                    ("Employee with this "+personnelCode+" personnelCode not found!"));

                    if (!employee.getPassword().equals(password))
                        throw new UserNotFoundException
                                ("Employee with this "+personnelCode+" personnelCode not found!");

                    AuthenticationEmployee.setLoggedInEmployee(employee);
                }

            } catch (Exception e) {

            }
        }
    }

    @Override
    public Employee save(Employee employee) {
     var bankRes=   ApplicationContext.getBankService().findBankByBossPersonnelCode();
        employee.setBank(bankRes);
        return super.save(employee);
    }

    @Override
    public Optional<Employee> findByPersonnelCode(Long personnelCode) {
        try (Session session = SessionFactoryInstance.sessionFactory.openSession()) {
            try {
                var res = employeeRepository.findByPersonnelCode(session, personnelCode);
                session.getTransaction().commit();
                return res;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new UserNotFoundException
                        ("employee with this personnelCode " + personnelCode + " not found!");
            }
        }
    }
}
