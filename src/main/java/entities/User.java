package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  private String name;
  private String phone;
  private String email;
  private Status status;
  public enum Status {WIP, DONE, CANCELLED}

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "user_show", joinColumns = {
          @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
          @JoinColumn(name = "show_id", referencedColumnName = "id")})
  private List<Concert> concerts;

  @ManyToOne(cascade = CascadeType.PERSIST)
    private Festival festival;

  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany(cascade = CascadeType.PERSIST)
  private List<Role> roleList = new ArrayList<>();




  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }

  public User() {

  }

  //TODO Change when password is hashed
   public boolean verifyPassword(String pw){
    return BCrypt.checkpw(pw, userPass);
    }

  public User(String userName, String userPass, String name, String phone, String email, Status status) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.status = status;
    this.concerts = new ArrayList<>();
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Concert> getShows() {
    return this.concerts;
  }

  public void setShows(List<Concert> concerts) {
    this.concerts = concerts;
  }

  public void addShow(Concert concert){
    if (concert != null) {
      this.concerts.add(concert);
    }
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

  public Festival getFestival() {
    return festival;
  }

  public void setFestival(Festival festival) {
    this.festival = festival;
  }

//  public void addFestival(Festival festival) {
//    setFestival().add(festival);
//    this.festival = festival;
//  }


  @Override
  public String toString() {
    return "User{" +
            "userName='" + userName + '\'' +
            ", userPass='" + userPass + '\'' +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", status='" + status + '\'' +
            ", shows=" + concerts +
            '}';
  }
}