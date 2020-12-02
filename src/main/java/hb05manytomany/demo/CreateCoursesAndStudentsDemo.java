package hb05manytomany.demo;

import hb05manytomany.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Start transaction...");
            session.beginTransaction();
            System.out.println("Creating course...");
            Course course = new Course("TetrisCourse");
            System.out.println("Saving course...");
            session.save(course);

            Student student1 = new Student("Jozko", "Vajda", "jzkvjd@azet.sk");
            Student student2 = new Student("Andy", "Weir", "martan@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);


            System.out.println("Saving students...");
            session.save(student1);
            session.save(student2);

            System.out.println("Saved studednts: " + course.getStudents());
            System.out.println("Commit transaction...");
            session.getTransaction().commit();
            System.out.println("Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
