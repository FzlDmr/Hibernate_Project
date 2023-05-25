package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().
                configure("cfg.xml").
                addAnnotatedClass(Patient.class).
                buildSessionFactory();


        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();


            Patient patient = new Patient(12, "muhammed", "sezgin", "bas agrisi", 15);
            session.persist(patient);


            List<Patient> patients = session.createQuery("from Patient").list(); //tum hastalari gosterme
            System.out.println(patients);


            Patient patient1 = session.get(Patient.class, 1); //update islemi
            System.out.println(patient1.getName());
            patient1.setName("fazil");

            Patient patient2 = session.get(Patient.class, 1);//delete islemi
            session.remove(patient2);


            tx.commit();


        } finally {
            session.close();

        }


    }
}
