package org.esteco.anniversary.notification;

import org.esteco.anniversary.employee.Employee;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnniversaryNotification implements Notification {

    @Override
    public void notify(List<Employee> employees) {

    }

    @Override
    public List<Employee> pickEmployees(List<Employee> employees) {
        return employees.stream().filter(isJoiningAnniversary()).collect(Collectors.toList());
    }

    private Predicate<Employee> isJoiningAnniversary() {
        return employee -> {
            DateTime joinDate = new DateTime(employee.getJoinDate());
            GregorianCalendar calendar = new GregorianCalendar();
            return calendar.get(Calendar.DAY_OF_MONTH) == joinDate.getDayOfMonth() && calendar.get(Calendar.YEAR) == joinDate.getYear();
        };
    }
}
