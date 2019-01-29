package org.esteco.anniversary;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.esteco.anniversary.employee.Employee;
import org.esteco.anniversary.employee.manager.EmployeeDAO;
import org.esteco.anniversary.notification.Notification;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Component
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
                Logger.getLogger(NotificationEngine.class.getName()).log(Level.ERROR, "Cannot create Notification of the type " + aClass.getName(), e);
            }
        };
    }

    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(NotificationConfig.class, EmployeeConfig.class);
        NotificationEngine engine = ctx.getBean(NotificationEngine.class);
        Logger.getLogger(NotificationEngine.class.getName()).error("Started Engine");
        engine.run();
    }
}
