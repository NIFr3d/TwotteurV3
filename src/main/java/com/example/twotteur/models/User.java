package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String username;

    @Column
    private String biography;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String displayname;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public int getId(){
        return this.id;
    }

    public User(String email,String username,String password){
        this.email=email;
        this.username=username;
        this.password=password;
        this.displayname=username;
    }

    public User() {

    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return this.username;
    }
    public String getDisplayname(){return this.displayname;}

    public String getBiography(){return this.biography;}
    public Date getCreatedat(){return this.createdat;}

    public String getEmail() {
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
}