package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String nickname;

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

    public int getId(){
        return this.id;
    }

    public User(String email,String nickname,String password){
        this.email=email;
        this.nickname=nickname;
        this.password=password;
        this.displayname=nickname;
    }

    public User() {

    }

    public String getNickname() {
        return this.nickname;
    }
    public String getDisplayname(){return this.displayname;}

    public String getBiography(){return this.biography;}
    public Date getCreatedat(){return this.createdat;}
}