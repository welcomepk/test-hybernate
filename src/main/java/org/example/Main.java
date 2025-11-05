package org.example;

import org.example.dao.StudentDao;
import org.example.entity.Student;
import org.example.hibernateUtil.HibernateSessionFactoryProvider;

import java.util.List;
import java.util.Scanner;

public class Main {
    static final Scanner sc;
    static  {
        sc = new Scanner(System.in);
    }
    static Student createStudent() {
        Student student = new Student();
        System.out.println("Enter Student Name: ");
        student.setName(sc.next());
        System.out.println("Enter Student Email: ");
        student.setEmail(sc.next());
        return student;
    }
    static void printStudent(Student s) {
        if(s == null) {
            System.out.println("No student found");
            return;
        }
        System.out.printf("""
                        Student Id: %d
                        Name: %s
                        Email: %s
                        isActive: %s
                        ----------------------
                    """, s.getId(), s.getName(), s.getEmail(), s.isActive());
    }
    static void printStudents(List<Student> students) {
        System.out.println("--------  All Students ------\n");
        for (Student s : students) {
            printStudent(s);
        }
    }
    public static void main(String[] args) {
        HibernateSessionFactoryProvider.init();
        System.out.println("-------- Welcome to Student Management -------------");
        while(true) {
            System.out.println("Options: ");
            System.out.println("""
                    1. create a student
                    2. get all students
                    3. get student by id
                    4. remove student by id
                    5.exit
                    """);

            System.out.println("Choose option: ");
            int option = sc.nextInt();
            boolean shouldClose = false;
            switch(option) {
                case 1:
                    StudentDao.saveStudent(createStudent());
                    break;
                case 2:
                    List<Student> students = StudentDao.getAllStudents();
                    printStudents(students);
                    break;
                case 3, 4:
                    System.out.println("Enter student id: ");
                    long id = sc.nextInt();
                    Student s  = StudentDao.getStudentById(id);
                    if(option == 3)
                    printStudent(s);
                    else {
                        StudentDao.deleteStudent(id);
                    }
                    break;
                case 5:
                    shouldClose = true;
                    break;
            }
            if(shouldClose) break;
        }

    }
}