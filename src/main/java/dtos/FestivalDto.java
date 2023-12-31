package dtos;

import entities.Festival;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link entities.Festival}
 */
public class FestivalDto implements Serializable {
    private final Long id;
    private final String name;
    private final String city;
    private final String startDate;
    private final String duration;

    public FestivalDto(Festival festival) {
        this.id = festival.getId();
        this.name = festival.getName();
        this.city = festival.getCity();
        this.startDate = festival.getStartDate();
        this.duration = festival.getDuration();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FestivalDto entity = (FestivalDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.duration, entity.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, startDate, duration);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "city = " + city + ", " +
                "startDate = " + startDate + ", " +
                "duration = " + duration + ")";
    }
}