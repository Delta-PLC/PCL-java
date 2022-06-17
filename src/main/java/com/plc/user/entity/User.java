package com.plc.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plc.company.Entity.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER_REGISTRATION")
@Getter
@Setter
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


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles_data",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "com_id"),name ="com_id" ,referencedColumnName = "company_id")
    @JsonIgnoreProperties(value = "userList")
    private CompanyEntity companyEntityData;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String email, String encode, String mobileNumber, String city, String address) {
        this.username=username;
        this.email=email;
        this.password=encode;
        this.mobileNumber=mobileNumber;
        this.city=city;
        this.address=address;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "mobileNumber = " + mobileNumber + ", " +
                "datetime = " + datetime + ", " +
                "address = " + address + ", " +
                "city = " + city + ")";
    }
}
