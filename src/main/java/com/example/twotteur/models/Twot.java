package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Twot{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable=false)
    private boolean isanswer=false;

    @ManyToOne
    private User user;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public Twot() {

    }

    public String getText(){
        return this.text;
    }
    public Date getDate(){
        return this.createdat;
    }



    public Twot(User user,String text,boolean isanswer){
        this.user=user;
        this.text=text;
        this.isanswer=isanswer;
    }

    public long getId(){
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isanswer(){return this.isanswer;}
}