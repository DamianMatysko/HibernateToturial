package sk.kosickaakademia.damianmatysko.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sk.kosickaakademia.damianmatysko.Student;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Start transaction...");
            session.beginTransaction();

            List<Student> students =session.createQuery("from Student").list();

            displayStudents(students);

            students=session.createQuery("from Student s where s.lastName='Riddle' OR s.lastName='Vajda'").list();

            System.out.println("\n\nStudents who have lastname Riddle or Vajda");
            displayStudents(students);


            System.out.println("\n\nStudents whose email ends kosicakaakademia.com");

            students=session.createQuery("from Student s  where s.email like '%kosicakaakademia.com'").list();
            displayStudents(students);

            System.out.println("Commit transaction...");
            session.getTransaction().commit();
            System.out.println("Success!!!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student student: students){
            System.out.println(student);
        }
    }
}
