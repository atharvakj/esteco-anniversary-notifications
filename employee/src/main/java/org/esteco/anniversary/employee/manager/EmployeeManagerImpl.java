package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.employee.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class EmployeeManagerImpl implements EmployeeManager {

    private SessionFactory sessionFactory;

    public EmployeeManagerImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int save(Employee emp) {
        Session session = sessionFactory.openSession();
        int id = (int) session.save(emp);
        session.close();
        return id;
    }

    @Override
    public Employee get(int empId) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
