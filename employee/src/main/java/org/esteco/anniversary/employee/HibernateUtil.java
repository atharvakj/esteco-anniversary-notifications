package org.esteco.anniversary.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() throws RuntimeException {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Employee.class);
            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
        } catch (Exception e){
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, "Could not create session factory",e);
            throw new RuntimeException("Could not create session factory",e);
        }
    }

    @Bean
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
