package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class WSToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false,unique = true)
    private User user;

    @Column(nullable = false,unique = true)
    private String token;

    public WSToken(){}

    public WSToken(User user,String token){
        this.user=user;
        this.token=token;
    }
    public String gettoken(){
        return this.token;
    }

    public void settoken(String token) {
        this.token=token;
    }

    public User getuser() {
        return this.user;
    }
}
