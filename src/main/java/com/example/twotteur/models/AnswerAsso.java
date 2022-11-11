package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class AnswerAsso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Twot originaltwot;

    @ManyToOne
    private Twot answer;

    public AnswerAsso(){

    }
    public AnswerAsso(Twot originaltwot,Twot answer){
        this.originaltwot=originaltwot;
        this.answer=answer;
    }
    public Twot getanswer(){
        return this.answer;
    }
    public Twot getoriginal(){
        return this.originaltwot;
    }
}
