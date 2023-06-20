package dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link entities.Washing_assistant}
 */
public class Washing_assistantDTO implements Serializable {
    private final Long id;
    private final String name;
    private final String primary_language;
    private final String years_of_experience;
    private final int price_per_hour;

    public Washing_assistantDTO(Long id, String name, String primary_language, String years_of_experience, int price_per_hour) {
        this.id = id;
        this.name = name;
        this.primary_language = primary_language;
        this.years_of_experience = years_of_experience;
        this.price_per_hour = price_per_hour;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrimary_language() {
        return primary_language;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public int getPrice_per_hour() {
        return price_per_hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Washing_assistantDTO entity = (Washing_assistantDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.primary_language, entity.primary_language) &&
                Objects.equals(this.years_of_experience, entity.years_of_experience) &&
                Objects.equals(this.price_per_hour, entity.price_per_hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, primary_language, years_of_experience, price_per_hour);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "primary_language = " + primary_language + ", " +
                "years_of_experience = " + years_of_experience + ", " +
                "price_per_hour = " + price_per_hour + ")";
    }
}