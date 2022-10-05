package com.example.twotteur.models;

import javax.persistence.*;

@Entity
public class UserModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    public int getId(){
        return this.id;
    }

    public UserModel(String email,String nickname,String password){
        this.email=email;
        this.nickname=nickname;
        this.password=password;
    }

    public UserModel() {

    }

    public String getNickname() {
        return this.nickname;
    }
}