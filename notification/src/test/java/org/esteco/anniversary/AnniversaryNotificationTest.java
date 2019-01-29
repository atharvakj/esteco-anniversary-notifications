package org.esteco.anniversary;

import org.esteco.anniversary.employee.Employee;
import org.esteco.anniversary.notification.AnniversaryNotification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AnniversaryNotificationTest {

    @Test
    public void testGetEmployeesWhenJoiningAnniversary() {
        AnniversaryNotification annNotif = new AnniversaryNotification();
        Employee e1 = new Employee();
        Date joinDate = new Date();
        e1.setJoinDate(joinDate);
        Employee e2 = new Employee();
        e2.setJoinDate(new Date(1995, 07, 07));
        List<Employee> emps = new ArrayList<>();
        Collections.addAll(emps,e1,e2);

        List<Employee> pickedEmployees = annNotif.pickEmployees(emps);

        assertTrue(pickedEmployees.get(0).getJoinDate().compareTo(joinDate) == 0);
    }
}
