package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
@NamedQuery(name = "Booking.deleteAllRows", query = "delete FROM Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String date_and_time;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToMany(mappedBy = "bookingsList", cascade = CascadeType.PERSIST)
    private List<Washing_assistant> washingAssistantsList;



    public Booking() {
    }

    public Booking(String date_and_time, String duration) {
        this.date_and_time = date_and_time;
        this.duration = duration;
        this.washingAssistantsList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Washing_assistant> getWashingAssistantsList() {
        return washingAssistantsList;
    }

    public void setWashingAssistantsList(List<Washing_assistant> washingAssistantsList) {
        this.washingAssistantsList = washingAssistantsList;
    }

    public void addWashingAssistants(Washing_assistant wa) {
        washingAssistantsList.add(wa);
        wa.addBooking(this);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", date_and_time='" + date_and_time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}