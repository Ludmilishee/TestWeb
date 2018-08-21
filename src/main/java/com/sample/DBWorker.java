package com.sample;

import com.sample.model.WeatherData;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DBWorker {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    public static void configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void save(WeatherData weatherData) {

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

    public static List<WeatherData> getTableData() {
        List<WeatherData> tableData;
        Session session = null;

        try
        {
            session = sessionFactory.openSession();
            tableData = session.createCriteria(WeatherData.class).list();
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

    public static List<WeatherData> getWeatherByDateAndCity(String startDateStr, String endDateStr, String city) {
        List<WeatherData> tableData;
        Session session = null;

        try
        {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(WeatherData.class);
            if (startDateStr.length() != 0) {
                Date startDate = formatter.parse(startDateStr + " 00:00:00");
                criteria.add(Restrictions.ge("date", startDate));
            }
            if (endDateStr.length() != 0) {
                Date endDate = formatter.parse(endDateStr + " 23:59:59");
                criteria.add(Restrictions.le("date", endDate));
            }
            if (!city.equals("none")) {
                criteria.add(Restrictions.eq("city", city));
            }
            tableData = criteria.list();
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
