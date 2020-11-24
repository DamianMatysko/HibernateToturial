package hb03onetomany.demo;

import hb03onetomany.demo.entity.Course;
import hb03onetomany.demo.entity.Instructor;
import hb03onetomany.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorCoursesDemo {
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

            System.out.println("Get instructor from DB...");
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Instructor: "+instructor);
            System.out.println("Courses: "+instructor.getCourses());

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
