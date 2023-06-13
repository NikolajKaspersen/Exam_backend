package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "festival")
public class Festival  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String city;
    private String startDate;
    private String duration;

    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL)
    private List<User> guests;

    public Festival() {
    }

    public Festival(String name, String city, String startDate, String duration, List<User> guests) {
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.duration = duration;
        this.guests = guests;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Metode til at tilføje en Guest til listen af guests
    public void addUser(User guest) {
        guests.add(guest);
        guest.setFestival(this);
    }

    // Metode til at fjerne en Guest fra listen af guests
    public void removeUser(User guest) {
        guests.remove(guest);
        guest.setFestival(null);
    }

}