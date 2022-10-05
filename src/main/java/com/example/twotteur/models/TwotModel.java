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
    private boolean is_answer=false;

    @ManyToOne
    private TwotModel original_twot;

    @ManyToOne
    private UserModel user;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at=new Date();

    public TwotModel() {

    }

    public String getText(){
        return this.text;
    }
    public Date getDate(){
        return this.created_at;
    }

    public TwotModel(UserModel user,String text){
        this.user=user;
        this.text=text;
    }

    public TwotModel(UserModel user,String text,TwotModel twot){
        this.user=user;
        this.text=text;
        this.original_twot=twot;
        this.is_answer=true;
    }

    public int getId(){
        return this.id;
    }
}