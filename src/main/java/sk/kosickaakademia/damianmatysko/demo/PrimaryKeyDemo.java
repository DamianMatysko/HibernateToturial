package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating 3 new student object...");
            Student student1 =new Student("Zuzana", "Caputova", "Caputova@kosicakaakademia.com");
            Student student2 =new Student("Jozko", "Vajda", "Vajda@kosicakaakademia.com");
            Student student3 =new Student("Maria", "Terezia", "Terezia@kosicakaakademia.com");
            Student student4 =new Student("Vlado", "Meciar", "Vlado@kosicakaakademia.com");
            System.out.println("Start transaction...");
            session.beginTransaction();
            System.out.println("Saving the Students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.save(student4);
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
