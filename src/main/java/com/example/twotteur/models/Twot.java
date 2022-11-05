package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Twot{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable=false)
    private boolean isanswer=false;

    @ManyToOne(cascade = CascadeType.ALL)
    private Twot originaltwot;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Twot(User user,String text){
        this.user=user;
        this.text=text;
    }

    public Twot(User user,String text,Twot twot){
        this.user=user;
        this.text=text;
        this.originaltwot=twot;
        this.isanswer=true;
    }

    public long getId(){
        return this.id;
    }

    public User getUser() {
        return this.user;
    }
}