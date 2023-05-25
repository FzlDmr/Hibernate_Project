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
        Transaction tx=null;

        try{

            tx = session.beginTransaction();


            Patient patient = new Patient(12,"muhammed","sezgin","bas agrisi",15);
            session.save(patient);


            List<Patient> patients = session.createQuery("from Patient").list();
            System.out.println(patients);


            tx.commit();


        }
        finally{
            session.close();

        }


    }
}
