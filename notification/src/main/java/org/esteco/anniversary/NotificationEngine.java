package org.esteco.anniversary;

import org.esteco.anniversary.employee.Employee;
import org.esteco.anniversary.employee.manager.EmployeeDAO;
import org.esteco.anniversary.notification.Notification;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationEngine {

    @Autowired
    EmployeeDAO employees;

    public void run() {
        Reflections reflections = new Reflections("org.esteco.anniversary");
        Set<Class<? extends Notification>> notifiers = reflections.getSubTypesOf(Notification.class);
        List<Employee> employees = this.employees.getAll();

        notifiers.stream().forEach(notifyEmployees(employees));
    }

    private Consumer<Class<? extends Notification>> notifyEmployees(List<Employee> employees) {
        return aClass -> {
            try {
                Notification notification = aClass.newInstance();
                notification.notify(employees);
            } catch (Exception e) {
                Logger.getLogger(NotificationEngine.class.getName()).log(Level.SEVERE, "Cannot create Notification of the type " + aClass.getName(), e);
            }
        };
    }
}
