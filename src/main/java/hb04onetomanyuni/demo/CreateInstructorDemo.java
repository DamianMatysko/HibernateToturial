package hb04onetomanyuni.demo;



import hb04onetomanyuni.demo.entity.Course;
import hb04onetomanyuni.demo.entity.Instructor;
import hb04onetomanyuni.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            Instructor instructor = new Instructor("Susan", "Peto", "blabla@email.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.yputobe.com/johonm","shoot");


            System.out.println("Associate objects...");
            instructor.setInstructorDetail(instructorDetail);
            System.out.println("Start tranzaction...");
            session.beginTransaction();


            System.out.println("Saving instructor: " +instructor);
            session.save(instructor);
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
