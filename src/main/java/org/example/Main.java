package org.example;

import org.example.utils.SessionFactoryProvider;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        System.out.println(sessionFactory);
    }
}