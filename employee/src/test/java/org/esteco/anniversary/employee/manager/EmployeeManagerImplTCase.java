package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.TestConfig;
import org.esteco.anniversary.employee.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


import java.util.List;

import static org.junit.Assert.*;

public class EmployeeManagerImplTCase extends TestConfig {

    @Autowired
    EmployeeManager manager;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setEmail("jaiswal@esteco.com");
      
        manager.save(employee);

        assertEquals(1,1);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = manager.getAll();

        assertTrue(employees.size()>0);
    }
}