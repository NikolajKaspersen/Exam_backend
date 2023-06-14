package facades;

import dtos.ConcertDto;
import entities.Concert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ConcertFacade {

    private static ConcertFacade instance;
    private static EntityManagerFactory emf;

    private ConcertFacade() {
    }

    public static ConcertFacade getConcertFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ConcertFacade();
        }
        return instance;
    }

    public List<ConcertDto> getAll(){
        EntityManager em = emf.createEntityManager();
        List<Concert> concerts;
        try{
            TypedQuery<Concert> query = em.createQuery("SELECT c FROM Concert c", Concert.class);
            concerts = query.getResultList();
        }finally{
            em.close();
        }
        return ConcertDto.getDtos(concerts);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ConcertDto create(ConcertDto concertdto) {
        Concert concert = new Concert(concertdto.getName(), concertdto.getDuration(), concertdto.getLocation(), concertdto.getStartDate(),concertdto.getStartTime());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(concert);
            em.getTransaction().commit();
            return new ConcertDto(concert);
        } finally {
            em.close();
        }


    }

    public ConcertDto getById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Concert concert = em.find(Concert.class, id);
            return new ConcertDto(concert);
        } finally {
            em.close();
        }
    }

    public ConcertDto deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Concert concert = em.find(Concert.class, id);
            em.getTransaction().begin();
            em.remove(concert);
            em.getTransaction().commit();
            return new ConcertDto(concert);
        } finally {
            em.close();
        }
    }

    public ConcertDto update(ConcertDto concertdto) {
        EntityManager em = emf.createEntityManager();
        try {
            Concert concert = em.find(Concert.class, concertdto.getId());
            concert.setName(concertdto.getName());
            concert.setDuration(concertdto.getDuration());
            concert.setLocation(concertdto.getLocation());
            concert.setStartDate(concertdto.getStartDate());
            concert.setStartTime(concertdto.getStartTime());
            em.getTransaction().begin();
            em.merge(concert);
            em.getTransaction().commit();
            return new ConcertDto(concert);
        } finally {
            em.close();
        }
    }
}
