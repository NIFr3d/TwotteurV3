package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private TwotModel twot;

    public LikeModel(User user,TwotModel twot){
        this.user=user;
        this.twot=twot;
    }

    public LikeModel() {
    }
}
