package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;


public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
     int stidentID=1;
            System.out.println("Success!!! Student ID: " + stidentID);
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with ID: " +stidentID);
            Student student1 = session.get(Student.class, stidentID);
            System.out.println("Updating student...");
            student1.setFirstName("Scooby");
            session.getTransaction().commit();
            System.out.println("Done!");

            session=sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Update email for all students...");
            session.createQuery("update Student set email='doo@gmail.com'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
