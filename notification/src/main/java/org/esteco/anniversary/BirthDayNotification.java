package org.esteco.anniversary;

import org.esteco.anniversary.employee.Employee;
import org.joda.time.DateTime;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BirthDayNotification implements Notification {

    private Predicate<Employee> isBirthDay() {
        return employee -> {
            GregorianCalendar calender = new GregorianCalendar();
            DateTime empBirthDate = new DateTime(employee.getBirthDate());
            return calender.get(Calendar.DAY_OF_MONTH) == empBirthDate.getDayOfMonth() && calender.get(Calendar.YEAR) == empBirthDate.getYear();
        };
    }

    @Override
    public List<Employee> pickEmployees(List<Employee> emps) {
        return emps.stream().filter(isBirthDay()).collect(Collectors.toList());
    }

    @Override
    public void notify(List<Employee> employees) {

    }
}
