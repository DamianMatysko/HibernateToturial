package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;


public class CreateStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating new student object...");
            Student student =new Student("Paul", "Wall", "damianmatysko@kosicakaakademia.com");
            System.out.println("Start transaction...");
            session.beginTransaction();
            System.out.println("Saving the Student...");
            session.save(student);
            System.out.println("Commit transaction...");
            session.getTransaction().commit();
            System.out.println("Success!!!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
    }
}
