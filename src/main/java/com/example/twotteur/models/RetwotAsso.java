package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
@Entity
public class RetwotAsso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Twot originaltwot;

    @ManyToOne
    private Twot retwot;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public RetwotAsso(){

    }
    public RetwotAsso(Twot originaltwot,Twot retwot){
        this.originaltwot=originaltwot;
        this.retwot=retwot;
        this.createdat=new Date();
    }
    public Twot getretwot(){
        return this.retwot;
    }
    public Twot getoriginal(){
        return this.originaltwot;
    }
}
