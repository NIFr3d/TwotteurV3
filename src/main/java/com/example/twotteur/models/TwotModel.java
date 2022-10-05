package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TwotModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String text;

    @Column(nullable=false)
    private boolean isanswer=false;

    @ManyToOne
    private TwotModel originaltwot;

    @ManyToOne
    private UserModel user;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public TwotModel() {

    }

    public String getText(){
        return this.text;
    }
    public Date getDate(){
        return this.createdat;
    }

    public TwotModel(UserModel user,String text){
        this.user=user;
        this.text=text;
    }

    public TwotModel(UserModel user,String text,TwotModel twot){
        this.user=user;
        this.text=text;
        this.originaltwot=twot;
        this.isanswer=true;
    }

    public int getId(){
        return this.id;
    }
}