package facades;

import dtos.BookingDto;
import dtos.CarDto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Booking;
import entities.Car;

import java.util.List;
public class BookingFacade {

    private static BookingFacade instance;;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BookingFacade() {

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static BookingFacade getBookingFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookingFacade();
        }
        return instance;
    }

    public List<BookingDto> getAllBookings(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
            List<Booking> bookings = query.getResultList();
            return BookingDto.getDtos(bookings);
        }finally {
            em.close();
        }
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = new Booking(bookingDto.getDate_and_time(), bookingDto.getDuration());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
            return new BookingDto(booking);
        } finally {
            em.close();
        }

    }

    public BookingDto deleteBookingById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Booking booking = em.find(Booking.class, id);
            em.remove(booking);
            em.getTransaction().commit();
            return new BookingDto(booking);
        } finally {
            em.close();
        }
    }

    public BookingDto getBookingById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Booking booking = em.find(Booking.class, id);
            return new BookingDto(booking);
        }finally {
            em.close();
        }
    }

    public BookingDto editBooking(BookingDto bookingDto) {
        EntityManager em = emf.createEntityManager();
        try {
            Booking booking = em.find(Booking.class, bookingDto.getId());
            booking.setDate_and_time(bookingDto.getDate_and_time());
            booking.setDuration(bookingDto.getDuration());
            em.getTransaction().begin();
            em.merge(booking);
            em.getTransaction().commit();
            return new BookingDto(booking);
        } finally {
            em.close();
        }
    }






}
