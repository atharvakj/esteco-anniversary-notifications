package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.TestConfig;
import org.esteco.anniversary.employee.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeManagerImplTCase extends TestConfig {

    @Autowired
    EmployeeManager manager;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setEmail("jaiswal@esteco.com");
        manager.save(employee);

        List<Employee> employees = manager.getAll();

        assertEquals(1, employees.size());
    }
}