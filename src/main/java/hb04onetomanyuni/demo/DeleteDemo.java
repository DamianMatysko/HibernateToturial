package hb04onetomanyuni.demo;

import hb04onetomanyuni.demo.entity.Instructor;
import hb04onetomanyuni.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            //System.out.println("Found Instructor: " + instructor);

            if (instructor!=null){
                System.out.println("Deleting: + instructor");
                session.delete(instructor);
            }

            System.out.println("Start tranzaction...");
            session.beginTransaction();


            System.out.println("Commit transaction...");
            session.getTransaction().commit();
            System.out.println("Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
