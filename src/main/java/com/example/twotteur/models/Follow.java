package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User followed;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User follower;

    public Follow(){}

    public Follow(User followed, User follower){
        this.followed=followed;
        this.follower=follower;
    }

    public User getFollowed(){
        return this.followed;
    }
    public User getFollower(){
        return this.follower;
    }

    public long getId() {
        return id;
    }

}
