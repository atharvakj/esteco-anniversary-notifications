package org.esteco.anniversary.notification;

import org.esteco.anniversary.employee.Employee;
import org.esteco.anniversary.mail.Email;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BirthdayNotification implements Notification {

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
        //employees.stream().map(mapToEmail()).forEach(Transporter::transport);
    }

    private Function<Employee, Email> mapToEmail() {
        return employee -> Email.builder().to(employee.getEmail())
                .subject("Happy Birthday " + employee.getName())
                .body(getBody(employee)).build();
    }

    private String getBody(Employee employee) {
        return null;
    }
}
