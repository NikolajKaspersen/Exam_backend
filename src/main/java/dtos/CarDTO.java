package dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link entities.Car}
 */
public class CarDTO implements Serializable {
    private final Long id;
    private final int registration_number;
    private final String brand;
    private final String make;
    private final String year;
    private final List<BookingDTO> bookings;

    public CarDTO(Long id, int registration_number, String brand, String make, String year, List<BookingDTO> bookings) {
        this.id = id;
        this.registration_number = registration_number;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public int getRegistration_number() {
        return registration_number;
    }

    public String getBrand() {
        return brand;
    }

    public String getMake() {
        return make;
    }

    public String getYear() {
        return year;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO entity = (CarDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.registration_number, entity.registration_number) &&
                Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.make, entity.make) &&
                Objects.equals(this.year, entity.year) &&
                Objects.equals(this.bookings, entity.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registration_number, brand, make, year, bookings);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "registration_number = " + registration_number + ", " +
                "brand = " + brand + ", " +
                "make = " + make + ", " +
                "year = " + year + ", " +
                "bookings = " + bookings + ")";
    }
}