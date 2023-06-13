package dtos;

import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link entities.User}
 */
public class UserDto implements Serializable {
    @NotNull
    private final String userName;
    @NotNull
    @Size(min = 1, max = 255)
    private final String userPass;
    private final String name;
    private final String phone;
    private final String email;
    private final String status;

    public UserDto(User user) {

        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.status = String.valueOf(user.getStatus());
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.userPass, entity.userPass) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass, name, phone, email, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "userName = " + userName + ", " +
                "userPass = " + userPass + ", " +
                "name = " + name + ", " +
                "phone = " + phone + ", " +
                "email = " + email + ", " +
                "status = " + status + ")";
    }
}