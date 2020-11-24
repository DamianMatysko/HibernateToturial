package hb03onetomany.demo;

import hb03onetomany.demo.entity.Course;
import hb03onetomany.demo.entity.Instructor;
import hb03onetomany.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCoursesDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Start transaction...");
            session.beginTransaction();

            int id=10;
            Course course= session.get(Course.class, id);
            System.out.println("Deleting course: "+course);
            session.delete(course);

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
