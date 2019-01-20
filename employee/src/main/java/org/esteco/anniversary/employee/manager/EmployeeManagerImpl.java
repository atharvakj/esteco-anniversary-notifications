package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.employee.Employee;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeManagerImpl implements EmployeeManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Employee emp) {
        entityManager.persist(emp);

    }

    @Override
    public Employee get(int empId) {

        return entityManager.find(Employee.class,empId);

    }

    @Override
    public List<Employee> getAll() {
        TypedQuery<Employee> selectAllQuery = entityManager.createQuery("from Employee", Employee.class);
        return selectAllQuery.getResultList();
    }
}
