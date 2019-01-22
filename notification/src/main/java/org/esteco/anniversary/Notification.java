package org.esteco.anniversary;

import org.esteco.anniversary.employee.Employee;

import java.util.List;

public interface Notification {

    void notify(List<Employee> employees);

    List<Employee> pickEmployees(List<Employee> emps);
}
