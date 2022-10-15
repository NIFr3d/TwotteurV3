package com.example.twotteur.models;

import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user2;

    @Column
    private String text;

    public Message(){}

    public Message(User user1,User user2,String text){
        this.user2=user2;
        this.user1=user1;
        this.text=text;
    }
}
