package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        User u1 = new User("user", "test123", "nikoalj", "422616", "niir@-gmail.com", User.Status.WIP);
        User u2 = new User("admin", "test123", "nij", "42245416", "inrn@ferf.dk", User.Status.CANCELLED);
        User u3 = new User("user_admin", "test123", "Joakim", "5522152616", "kooar@-gmail.com", User.Status.DONE);


        Role userRole = new Role("guest");
        Role adminRole = new Role("guest_Admin");

        Festival f1 = new Festival("kurt", "roskild", "12-12-12", "12,12,12", new ArrayList<>());
        Festival f2 = new Festival("bo", "rosfgfild", "11-11-11", "11,11,11", new ArrayList<>());
        Festival f3 = new Festival("kurt", "Egadal", "9-9-9", "2,2,2", new ArrayList<>());

        Concert s1 = new Concert("kurt", "15", "stadium", "12,12,12", "12,12,12");
        Concert s2 = new Concert("bo", "11", "plasen", "11,11,11", "11,11,11");
        Concert s3 = new Concert("kurt", "9", "Egadal", "2,2,2", "2,2,2");

        u1.addRole(userRole);
        u2.addRole(adminRole);
        u3.addRole(userRole);

        em.getTransaction().begin();

        s1.addGuest(u1);
//        s1.setFestival(f1);
        s2.addGuest(u2);
//        s2.setFestival(f2);
        s3.addGuest(u3);

        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(f1);
        em.persist(f2);
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);

        em.getTransaction().commit();

        System.out.println("");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println("");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println("");

        em.close();


    }

}