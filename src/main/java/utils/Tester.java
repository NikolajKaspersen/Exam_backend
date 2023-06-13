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

        User u1 = new User("user", "test123","nikoalj", "422616","niir@-gmail.com", "12345678");
        User u2 = new User("admin", "test123","nij", "42245416", "inrn@ferf.dk", "12347647678");

        Role userRole = new Role("guest");
        Role adminRole = new Role("guest_Admin");

        Festival f1 = new Festival("kurt","roskild","12-12-12","12,12,12",new ArrayList<>());
        Festival f2 = new Festival("bo","rosfgfild","11-11-11","11,11,11",new ArrayList<>());

        Concert s1 = new Concert("kurt","15","stadium","12,12,12","12,12,12");
        Concert s2 = new Concert("bo","11","plasen","11,11,11","11,11,11");

        u1.addRole(userRole);
        u2.addRole(adminRole);

        em.getTransaction().begin();

        s1.addGuest(u1);
//        s1.setFestival(f1);
        s2.addGuest(u2);
//        s2.setFestival(f2);

        em.persist(u1);
        em.persist(u2);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(f1);
        em.persist(f2);
        em.persist(f1);
        em.persist(s2);

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