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
        Employee employee = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal", "EST0014");

        manager.save(employee);
        List<Employee> employees = manager.getAll();

        assertEquals(1, employees.size());
        assertEquals("EST0014", employees.get(0).getEmployeeId());
        assertEquals("Atharva", employees.get(0).getName());
        assertEquals("Jaiswal", employees.get(0).getSurname());
        assertEquals("jaiswal@esteco.com", employees.get(0).getEmail());
        assertEquals("20-10-2018", parseDate(employees.get(0).getJoinDate()));
        assertEquals("10-10-1994", parseDate(employees.get(0).getBirthDate()));
    }

    @Test
    public void testSaveMultipleEmployee() throws Exception {
        Employee employee1 = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal", "EST0014");
        Employee employee2 = createEmployee("honule@esteco.com", "Vivek", "Honule", "EST0015");

        manager.save(employee1);
        manager.save(employee2);
        List<Employee> employees = manager.getAll();

        assertEquals(2, employees.size());
    }

    @Test(expected = EstecoAnniversaryException.class)
    public void testSaveEmployeeWhoIsAlreadyExist() throws Exception {
        Employee employee1 = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal", "EST0014");
        Employee employee2 = createEmployee("honule@esteco.com", "Vivek", "Honule", "EST0014");

        manager.save(employee1);
        manager.save(employee2);
    }

    @Test(expected = EstecoAnniversaryException.class)
    public void testSaveEmployeeWithNullEmployee() throws Exception {
        manager.save(null);
    }

    @Test
    public void testGetEmployee() throws Exception {
        Employee employee1 = createEmployee("jaiswal@esteco.com", "Atharva", "Jaiswal", "EST0014");
        Employee employee2 = createEmployee("honule@esteco.com", "Vivek", "Honule", "EST0015");
        manager.save(employee1);
        manager.save(employee2);

        Employee employee = manager.get("EST0014");

        assertEquals("EST0014", employee.getEmployeeId());
        assertEquals("Atharva", employee.getName());
        assertEquals("Jaiswal", employee.getSurname());
        assertEquals("jaiswal@esteco.com", employee.getEmail());
    }

    private Employee createEmployee(String email, String name, String surname, String empId) throws ParseException {
        Employee employee = new Employee();
        employee.setEmployeeId(empId);
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

    private String parseDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        return format.format(date);
    }
}