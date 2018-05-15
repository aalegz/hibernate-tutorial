package com.aalegz.hibernate.demo;

import com.aalegz.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {

        //create session factory
        System.out.println("Session factory creation..");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        System.out.println("Session factory created!\n");

        //create session
        System.out.println("Session creation");
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Session created!\n");

        try {
            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            //display the students
            displayTheStudents(theStudents);

            //query students: lastName = 'Doe'
            theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();

            //display students
            System.out.println("\nStudents who has lastName 'Doe'");
            displayTheStudents(theStudents);

            //query students lastName 'Doe' or firstName 'Duffy'
            theStudents = session.createQuery("from Student s where"
                    + " s.lastName = 'Doe' OR s.firstName = 'Duffy'").getResultList();

            //display the students
            System.out.println("\nStudents lastName 'Doe' or firstName 'Duffy'");
            displayTheStudents(theStudents);

            //query students where email like '%mail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%mail.com'").getResultList();

            //display the students
            System.out.println("\nStudents where email like '%mail.com'");
            displayTheStudents(theStudents);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!");
        } finally {
            sessionFactory.close();
        }

    }

    private static void displayTheStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
