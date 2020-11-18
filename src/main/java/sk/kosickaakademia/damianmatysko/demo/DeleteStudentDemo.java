package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;


public class DeleteStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int stidentID = 1;
            System.out.println("Success!!! Student ID: " + stidentID);
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with ID: " + stidentID);
            Student student1 = session.get(Student.class, stidentID);
            System.out.println("Deleting student...");
           // session.delete(student1);

            System.out.println("Deleting student id=2...");
            session.createQuery("delete from Student where id=2").executeUpdate();


            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
