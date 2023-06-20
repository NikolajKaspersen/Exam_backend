package dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link entities.Booking}
 */
public class BookingDTO implements Serializable {
    private final Long id;
    private final String date_and_time;
    private final String duration;
    private final List<Washing_assistantDTO> washingAssistantsList;

    public BookingDTO(Long id, String date_and_time, String duration, List<Washing_assistantDTO> washingAssistantsList) {
        this.id = id;
        this.date_and_time = date_and_time;
        this.duration = duration;
        this.washingAssistantsList = washingAssistantsList;
    }

    public Long getId() {
        return id;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public String getDuration() {
        return duration;
    }

    public List<Washing_assistantDTO> getWashingAssistantsList() {
        return washingAssistantsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDTO entity = (BookingDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.date_and_time, entity.date_and_time) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.washingAssistantsList, entity.washingAssistantsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date_and_time, duration, washingAssistantsList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "date_and_time = " + date_and_time + ", " +
                "duration = " + duration + ", " +
                "washingAssistantsList = " + washingAssistantsList + ")";
    }
}