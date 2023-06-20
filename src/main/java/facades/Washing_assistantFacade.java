package facades;

import dtos.CarDto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dtos.Washing_assistantDto;
import entities.Car;
import entities.Washing_assistant;

import java.util.List;

public class Washing_assistantFacade {

    private static Washing_assistantFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Washing_assistantFacade() {

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static Washing_assistantFacade getWashing_assistantFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Washing_assistantFacade();
        }
        return instance;
    }

    public List<Washing_assistantDto> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Washing_assistant> washingAssistants;
        try {
            TypedQuery<Washing_assistant> query = em.createQuery("SELECT w FROM Washing_assistant w", Washing_assistant.class);
            washingAssistants = query.getResultList();
            return Washing_assistantDto.getDtos(washingAssistants);
        } finally {
            em.close();
        }
    }

    public Washing_assistantDto create(Washing_assistantDto washingAssistantDto) {
        Washing_assistant washingAssistant = new Washing_assistant(washingAssistantDto.getName(), washingAssistantDto.getPrimary_language(), washingAssistantDto.getYears_of_experience(), washingAssistantDto.getPrice_per_hour());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(washingAssistant);
            em.getTransaction().commit();
            return new Washing_assistantDto(washingAssistant);
        } finally {
            em.close();
        }
    }

    public Washing_assistantDto getById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Washing_assistant washingAssistant = em.find(Washing_assistant.class, id);
            return new Washing_assistantDto(washingAssistant);
        }finally {
            em.close();
        }
    }

    public Washing_assistantDto deleteById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Washing_assistant washingAssistant = em.find(Washing_assistant.class, id);
            em.getTransaction().begin();
            em.remove(washingAssistant);
            em.getTransaction().commit();
            return new Washing_assistantDto(washingAssistant);
        }finally {
            em.close();
        }
    }

    public Washing_assistantDto edit(Washing_assistantDto washingAssistantDto){
        EntityManager em = emf.createEntityManager();
        try{
            Washing_assistant washingAssistant = em.find(Washing_assistant.class, washingAssistantDto.getId());
            washingAssistant.setName(washingAssistantDto.getName());
            washingAssistant.setPrimary_language(washingAssistantDto.getPrimary_language());
            washingAssistant.setYears_of_experience(washingAssistantDto.getYears_of_experience());
            washingAssistant.setPrice_per_hour(washingAssistantDto.getPrice_per_hour());
            em.getTransaction().begin();
            em.merge(washingAssistant);
            em.getTransaction().commit();
            return new Washing_assistantDto(washingAssistant);
        }finally {
            em.close();
        }
    }
}
