package hb05manytomany.demo;

import hb05manytomany.demo.entity.Course;
import hb05manytomany.demo.entity.Instructor;
import hb05manytomany.demo.entity.InstructorDetail;
import hb05manytomany.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Start transaction...");
            session.beginTransaction();

            Course course=new Course("PacmanCourse");
            course.addReview(new Review("wow"));
            course.addReview(new Review("nice"));
            course.addReview(new Review("wtW"));

            System.out.println("Saving course: " + course);
            System.out.println(course.getReviews());
            session.save(course);

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
