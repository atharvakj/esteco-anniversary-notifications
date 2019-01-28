package org.esteco.anniversary.employee.manager;

import org.esteco.anniversary.EstecoAnniversaryException;
import org.esteco.anniversary.TestConfig;
import org.esteco.anniversary.employee.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeeDAOImplITCase extends TestConfig {

    @Autowired
    private EmployeeDAO manager;

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal");
        manager.save(employee);

        List<Employee> employees = manager.getAll();

        assertEquals(1, employees.size());
    }

    @Test
    public void testSaveMultipleEmployee() throws Exception {
        Employee employee1 = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal");
        Employee employee2 = createEmployee("honule@esteco.com", "Vivek", "Honule");

        manager.save(employee1);
        manager.save(employee2);
        List<Employee> employees = manager.getAll();

        assertEquals(2, employees.size());
    }

    @Test(expected = EstecoAnniversaryException.class)
    public void testSaveEmployeeWithEmptyEmployee() throws Exception {
        manager.save(null);
    }

    private Employee createEmployee(String email, String name, String surname) throws ParseException {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setName(name);
        employee.setSurname(surname);
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date birthDate = format.parse("10-10-1994");
        Date joiningDate = format.parse("20-10-2018");
        employee.setBirthDate(birthDate);
        employee.setJoinDate(joiningDate);
        return employee;
    }
}