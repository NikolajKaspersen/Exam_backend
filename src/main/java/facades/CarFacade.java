package facades;

import dtos.CarDto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entities.Car;

import java.util.List;

public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {

    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CarDto getCarById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, id);
            return new CarDto(car);
        }finally {
            em.close();
        }
    }

    public CarDto createCar(CarDto carDto) {
        Car car = new Car(carDto.getRegistration_number(), carDto.getBrand(), carDto.getMake(), carDto.getYear());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            return new CarDto(car);
        } finally {
            em.close();
        }

    }

    public CarDto deleteById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, id);
            em.getTransaction().begin();
            em.remove(car);
            em.getTransaction().commit();
            return new CarDto(car);
        } finally {
            em.close();
        }
    }

    public CarDto update(CarDto carDto) {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, carDto.getId());
            car.setRegistration_number(carDto.getRegistration_number());
            car.setBrand(carDto.getBrand());
            car.setMake(carDto.getMake());
            car.setYear(carDto.getYear());
            em.getTransaction().begin();
            em.merge(car);
            em.getTransaction().commit();
            return new CarDto(car);
        } finally {
            em.close();
        }
    }







}
