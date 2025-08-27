package com.example.databaseeval;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(College.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            seedData(sessionFactory);
            printAllColleges(sessionFactory);
        }
    }

    private static void seedData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            if (((Number) session.createSelectionQuery("select count(c) from College c").getSingleResult()).intValue() == 0) {
                session.persist(new College(101, "CU", "Punjab"));
                session.persist(new College(102, "DU", "Delhi"));
                session.persist(new College(103, "PU", "Patna"));
            }

            transaction.commit();
        }
    }

    private static void printAllColleges(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            List<College> colleges = session.createQuery("from College", College.class).list();
            System.out.println("cid\tcName\tclocation");
            for (College college : colleges) {
                System.out.printf("%d\t%s\t%s%n", college.getCid(), college.getCName(), college.getCLocation());
            }
        }
    }
}


