package dtos;

import entities.Concert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link entities.Concert}
 */
public class ConcertDto implements Serializable {
    private final Long id;
    private final String name;
    private final String duration;
    private final String location;
    private final String startDate;
    private final String startTime;

    public ConcertDto(Concert concert) {
        this.id = concert.getId();
        this.name = concert.getName();
        this.duration = concert.getDuration();
        this.location = concert.getLocation();
        this.startDate = concert.getStartDate();
        this.startTime = concert.getStartTime();
    }

    public static List<ConcertDto> getDtos(List<Concert> concerts){
        List<ConcertDto> concertDtos = new ArrayList<>();
        concerts.forEach(concert->concertDtos.add(new ConcertDto(concert)));
        return concertDtos;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertDto entity = (ConcertDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.startTime, entity.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, location, startDate, startTime);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "duration = " + duration + ", " +
                "location = " + location + ", " +
                "startDate = " + startDate + ", " +
                "startTime = " + startTime + ")";
    }
}