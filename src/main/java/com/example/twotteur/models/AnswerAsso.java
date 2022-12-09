package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AnswerAsso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Twot originaltwot;

    @ManyToOne
    private Twot answer;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public AnswerAsso(){

    }
    public AnswerAsso(Twot originaltwot,Twot answer){
        this.originaltwot=originaltwot;
        this.answer=answer;
        this.createdat=new Date();
    }
    public Twot getanswer(){
        return this.answer;
    }
    public Twot getoriginal(){
        return this.originaltwot;
    }
}
