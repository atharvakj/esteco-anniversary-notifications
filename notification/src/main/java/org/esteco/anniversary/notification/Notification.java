package org.esteco.anniversary.notification;

import org.esteco.anniversary.employee.Employee;

import java.util.List;

public interface Notification {

    void notify(List<Employee> employees);

    List<Employee> pickEmployees(List<Employee> emps);
}
