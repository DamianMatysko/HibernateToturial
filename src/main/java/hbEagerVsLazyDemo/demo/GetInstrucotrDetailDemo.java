package hbEagerVsLazyDemo.demo;

import hbEagerVsLazyDemo.demo.entity.Instructor;
import hbEagerVsLazyDemo.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstrucotrDetailDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Start tranzaction...");
            session.beginTransaction();
            int id = 2 ;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            System.out.println("InstructorDetail: "+instructorDetail);
            System.out.println("The associated instructor: "+instructorDetail.getInstructor());



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
