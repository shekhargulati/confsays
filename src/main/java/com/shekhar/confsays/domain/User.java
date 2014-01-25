package com.shekhar.confsays.domain;

import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.find", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"),
        @NamedQuery(name = "User.list", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "UserRoles", joinColumns = {@JoinColumn(name = "userId")})
    @Column(name = "role")
    private List<Role> roles;


    private Date registeredOn = new Date();


    public User() {

    }


    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = new Sha256Hash(password).toHex();
        this.roles = new ArrayList<Role>();
        this.roles.add(Role.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

}