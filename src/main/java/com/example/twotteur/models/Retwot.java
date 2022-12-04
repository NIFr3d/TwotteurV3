package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Retwot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Twot twot;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public Retwot() {}

    public Retwot(User user,Twot twot){
        this.user=user;
        this.twot=twot;
    }

    public Retwot(User user, String text,Twot twot){
        this.user=user;
        this.text=text;
        this.twot=twot;
    }

    public long getId(){
        return this.id;
    }
    public String getText(){
        return this.text;
    }
    public Date getDate(){
        return this.createdat;
    }
    public User getUser() {
        return this.user;
    }
}
