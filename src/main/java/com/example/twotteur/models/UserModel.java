package com.example.twotteur.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="email", nullable = false,unique = true,columnDefinition = "varchar")
    private String email;

    @Column(name="nickname",nullable = false,unique = true,columnDefinition = "varchar")
    private String nickname;

    @Column(name="password",nullable = false,columnDefinition = "varchar")
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
}