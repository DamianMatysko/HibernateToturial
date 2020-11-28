package hbEagerVsLazyDemo.demo;

import hbEagerVsLazyDemo.demo.entity.Course;
import hbEagerVsLazyDemo.demo.entity.Instructor;
import hbEagerVsLazyDemo.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("luv2code: Start transaction...");
            session.beginTransaction();

            System.out.println("luv2code:Get instructor from DB...");
            int id = 1;
            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                    "join fetch  i.courses " +
                    "where i.id=:theInstructorId",
                    Instructor.class);

            query.setParameter("theInstructorId", id);
            Instructor instructor= query.getSingleResult();

            System.out.println("luv2code:Instructor: " + instructor);


            System.out.println("luv2code:Commit transaction...");
            session.getTransaction().commit();
            session.close();
            System.out.println("luv2code: sesson is close");
            System.out.println("luv2code: " + instructor.getCourses());
            System.out.println("luv2code:Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
