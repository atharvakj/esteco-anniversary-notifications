package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeManager {

    int save(Employee emp);

    Employee get(int empId);

    List<Employee> getAll();
}
