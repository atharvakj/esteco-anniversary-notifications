package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.EstecoAnniversaryException;
import org.esteco.anniversary.employee.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Employee employee) throws EstecoAnniversaryException {
        try {
            entityManager.persist(employee);
        } catch (Exception e) {
            throw new EstecoAnniversaryException("Error occurred while saving employee", e);
        }
    }

    @Override
    public Employee get(String empId) {
        return entityManager.find(Employee.class, empId);

    }

    @Override
    public List<Employee> getAll() {
        TypedQuery<Employee> selectAllQuery = entityManager.createQuery("from Employee", Employee.class);
        return selectAllQuery.getResultList();
    }
}

