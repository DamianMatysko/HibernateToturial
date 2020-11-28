package hb04onetomanyuni.demo;

import hb04onetomanyuni.demo.entity.Course;
import hb04onetomanyuni.demo.entity.Instructor;
import hb04onetomanyuni.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesDemo {
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
            int id = 3;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Creating courses...");
            Course course1 = new Course("Heavy metal guitar");
            Course course2 = new Course("Air-soft masterclass");

            System.out.println("Add courses to instructor...");
            instructor.add(course1);
            instructor.add(course2);

            System.out.println("Saving...");
            session.save(course1);
            session.save(course2);

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
