package dtos;

import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link entities.User}
 */
public class UserDTO implements Serializable {
    @NotNull
    private final String userName;
    @NotNull
    @Size(min = 1, max = 255)
    private final String userPass;
    private final List<CarDTO> cars;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.cars = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public List<CarDTO> getCars() {
        return cars;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO entity = (UserDTO) o;
        return Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.userPass, entity.userPass) &&
                Objects.equals(this.cars, entity.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass, cars);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "userName = " + userName + ", " +
                "userPass = " + userPass + ", " +
                "cars = " + cars + ")";
    }
}