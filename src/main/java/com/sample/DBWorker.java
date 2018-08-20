package com.sample;

import com.sample.model.WeatherData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBWorker {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static void configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void save(WeatherData weatherData) {
        configureSessionFactory();

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(weatherData);

            session.flush();
            tx.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<WeatherData> getTableData(Class tableClass) {
        List<WeatherData> tableData;
        Session session = null;

        try
        {
            session = sessionFactory.openSession();
            tableData = session.createCriteria(tableClass).list();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            tableData = new ArrayList<>();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return tableData;
    }
}
