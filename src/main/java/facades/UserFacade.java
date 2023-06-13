package facades;

import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import security.errorhandling.AuthenticationException;

public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }


    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public User getUser(String username){
        EntityManager em = emf.createEntityManager();
        try{
            User user = em.find(User.class, username);
            return user;
        }finally{
            em.close();
        }
    }

    public User createUser(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }finally{
            em.close();
        }
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User deleteUser(String username){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if(user == null){
                throw new IllegalArgumentException("No user with that username");
            }
            em.remove(user);
            em.getTransaction().commit();
            return user;
        }finally{
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    public User editUser(Long id, String username, String password, String firstName, String phone, String email, User.Status status){
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        user.setUserName(username);
        user.setUserPass(password);
        user.setName(firstName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setStatus(status);
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user;
        }finally{
            em.close();
        }
    }

}
