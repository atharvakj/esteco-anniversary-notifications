package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.EstecoAnniversaryException;
import org.esteco.anniversary.employee.Employee;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public interface EmployeeDAO {

    void save(Employee emp) throws EstecoAnniversaryException;

    Employee get(int empId);

    List<Employee> getAll();
}

