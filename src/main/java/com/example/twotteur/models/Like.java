package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Twot twot;

    public Like(User user,Twot twot){
        this.user=user;
        this.twot=twot;
    }

    public Like() {
    }
}
