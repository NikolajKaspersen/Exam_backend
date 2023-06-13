package facades;

import dtos.FestivalDto;
import entities.Festival;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

public class FestivalFacade {
    private static FestivalFacade instance;
    private static EntityManagerFactory emf;

    private FestivalFacade() {
    }

    public static FestivalFacade getFestivalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FestivalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FestivalDto create(FestivalDto festivaldto) {
        Festival festival = new Festival(festivaldto.getName(), festivaldto.getCity(), festivaldto.getStartDate(), festivaldto.getDuration(), new ArrayList<>());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(festival);
            em.getTransaction().commit();
            return new FestivalDto(festival);
        } finally {
            em.close();
        }


    }

    public FestivalDto getById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Festival festival = em.find(Festival.class, id);
            return new FestivalDto(festival);
        } finally {
            em.close();
        }
    }

    public FestivalDto deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Festival festival = em.find(Festival.class, id);
            em.getTransaction().begin();
            em.remove(festival);
            em.getTransaction().commit();
            return new FestivalDto(festival);
        } finally {
            em.close();
        }
    }

    public FestivalDto edit(FestivalDto festivaldto) {
        EntityManager em = emf.createEntityManager();
        try {
            Festival festival = em.find(Festival.class, festivaldto.getId());
            festival.setName(festivaldto.getName());
            festival.setCity(festivaldto.getCity());
            festival.setStartDate(festivaldto.getStartDate());
            festival.setDuration(festivaldto.getDuration());
            em.getTransaction().begin();
            em.merge(festival);
            em.getTransaction().commit();
            return new FestivalDto(festival);
        } finally {
            em.close();
        }
    }

}
