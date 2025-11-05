package org.example.dao;

import org.example.entity.Student;
import org.example.hibernateUtil.HibernateSessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {
    public static void saveStudent(Student s) {
        Transaction tx = null;
        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.persist(s);
            tx.commit();
        }catch(Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public static Student getStudentById(long id) {
        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }
    public static void updateStudent(Student s) {
        Transaction tx = null;
        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(s);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public static void deleteStudent(long id) {
        Transaction tx = null;
        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student s = session.get(Student.class, id);
            if(s != null) {
                session.remove(s);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public static List<Student> getAllStudents() {
        try(Session session = HibernateSessionFactoryProvider.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}
