//package entities;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "guest")
//public class Guest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    private String name;
//    private String phone;
//    private String email;
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "festival_id", referencedColumnName = "id")
//    private Festival festival;
//
//    @ManyToMany
//    @JoinTable(name = "guest_show",
//            joinColumns = @JoinColumn(name = "guest_id"),
//            inverseJoinColumns = @JoinColumn(name = "show_id"))
//    private List<Show> shows;
//
//    public Guest() {
//    }
//
//    public Guest(String name, String phone, String email, String status) {
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.status = status;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//
//
//
//
//}