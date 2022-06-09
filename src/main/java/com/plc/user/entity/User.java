package com.plc.user.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="USER_REGISTRATION")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private String mobileNumber;
    private Date datetime;
    private String address;
    private String city;
    @PrePersist
    private void Createdate() {
        datetime = new Date();
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private Set<Role> roles;


    public User(String username, String email, String encode, String mobileNumber, String city, String address) {
        this.username=username;
        this.email=email;
        this.password=encode;
        this.mobileNumber=mobileNumber;
        this.city=city;
        this.address=address;
    }
}
