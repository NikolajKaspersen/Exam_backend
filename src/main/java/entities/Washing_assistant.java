package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "washing_assistant")
@NamedQuery(name = "Washing_assistant.deleteAllRows", query = "delete FROM Washing_assistant")
public class Washing_assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String primary_language;
    private String years_of_experience;
    private int price_per_hour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "washing_assistant_booking",
            joinColumns = @JoinColumn(name = "washing_assistant_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id")
    )
    private List<Booking> bookingsList;



    public Washing_assistant() {
    }

    public Washing_assistant(String name, String primary_language, String years_of_experience, int price_per_hour) {
        this.name = name;
        this.primary_language = primary_language;
        this.years_of_experience = years_of_experience;
        this.price_per_hour = price_per_hour;
        this.bookingsList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimary_language() {
        return primary_language;
    }

    public void setPrimary_language(String primary_language) {
        this.primary_language = primary_language;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(String years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public int getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public List<Booking> getBookingsList() {
        return bookingsList;
    }

    public void setBookingsList(List<Booking> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public void addBooking(Booking booking) {
        this.bookingsList.add(booking);
    }



    @Override
    public String toString() {
        return "Washing_assistant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", primary_language='" + primary_language + '\'' +
                ", years_of_experience='" + years_of_experience + '\'' +
                ", price_per_hour=" + price_per_hour +
                '}';
    }
}