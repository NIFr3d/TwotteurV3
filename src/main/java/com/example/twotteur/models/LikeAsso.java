package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class LikeAsso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Twot twot;

    public LikeAsso(Twot twot, User user) {
        this.twot=twot;
        this.user=user;
    }

    public LikeAsso() {

    }
}
