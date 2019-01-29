package org.esteco.anniversary.notification;

import org.esteco.anniversary.employee.Employee;
import org.esteco.anniversary.mail.Email;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnniversaryNotification implements Notification {

    @Override
    public List<Employee> pickEmployees(List<Employee> employees) {
        return employees.stream().filter(isJoiningAnniversary()).collect(Collectors.toList());
    }

    @Override
    public void notify(List<Employee> employees) {
        pickEmployees(employees).stream().map(employee -> mapToEmail(employee)).forEach(Transporter::transport);
    }

    private Email mapToEmail(Employee employee) {
        return Email.builder()
                .type("Anniversary")
                .subject("Congratulations " + employee.getName() + " for completing " + serviceYears(employee) + " years at Esteco")
                .to(employee.getEmail())
                .from("jaiswal@esteco.com")
                .build();
    }

    private Predicate<Employee> isJoiningAnniversary() {
        return employee -> {
            DateTime joinDate = new DateTime(employee.getJoinDate());
            GregorianCalendar calendar = new GregorianCalendar();
            return calendar.get(Calendar.DAY_OF_MONTH) == joinDate.getDayOfMonth() && calendar.get(Calendar.MONTH) + 1 == joinDate.getMonthOfYear();
        };
    }

    private int serviceYears(Employee employee) {
        return (new GregorianCalendar()).get(Calendar.YEAR) - new DateTime(employee.getJoinDate()).getYear();
    }
}
