package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;


public class ReadStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating new student object...");
            Student student = new Student("Tom", "Riddle", "voldemord@kosicakaakademia.com");
            System.out.println("Start transaction...");
            session.beginTransaction();
            System.out.println("Saving the Student...  " + student);
            session.save(student);
            System.out.println("Commit transaction...");
            session.getTransaction().commit();


            System.out.println("Success!!! Student ID: " + student.getId());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with ID: " + student.getId());
            Student student1 = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + student1);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
