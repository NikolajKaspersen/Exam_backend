package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test123");
    User admin = new User("admin", "test123");
    User both = new User("user_admin", "test123");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");


    Car car1 = new Car(123456, "BMW", "M3", "2019");
    Car car2 = new Car(654321, "Audi", "A4", "2018");
    Car car3 = new Car(135246, "Mercedes", "C63", "2017");

    user.addCar(car1);
    user.addCar(car2);
    admin.addCar(car3);

    Booking b1 = new Booking("2019-12-01", "2019-12-05");
    Booking b2 = new Booking("2019-12-06", "2019-12-10");
    Booking b3 = new Booking("2019-12-11", "2019-12-15");

//    b1.setCar(car1);
//    b2.setCar(car2);
//    b3.setCar(car3);

    car1.addBookings(b1);
    car2.addBookings(b2);
    car3.addBookings(b3);

    Washing_assistant wa1 = new Washing_assistant("John", "English","18 years",20);
    Washing_assistant wa2 = new Washing_assistant("Peter", "Danish","20 years",25);
    Washing_assistant wa3 = new Washing_assistant("Hans", "German","22 years",30);

    b1.addWashingAssistants(wa1);
    b2.addWashingAssistants(wa2);
    b3.addWashingAssistants(wa3);

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
