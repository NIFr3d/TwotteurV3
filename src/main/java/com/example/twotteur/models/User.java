package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String username;

    @Column
    private String biography;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String picture;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public long getId(){
        return this.id;
    }

    public User(String email,String username,String password){
        this.email=email;
        this.username=username;
        this.password=password;
        this.nickname=username;
        this.picture="../images/default.png";
    }

    public User() {

    }

    public String getusername() {
        return this.username;
    }
    public String getnickname(){return this.nickname;}

    public String getBiography(){return this.biography;}
    public Date getCreatedat(){return this.createdat;}

    public String getPicture(){return this.picture;}

    public long getid(){return this.id;}

    public void setbiography(String biography) {
        this.biography=biography;
    }
    public void setnickname(String nickname){
        this.nickname=nickname;
    }
    public void setpicture(String picture){
        this.picture=picture;
    }
}