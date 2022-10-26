package com.example.twotteur.models;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user2;

    @Column
    private String text;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdat=new Date();

    public Message(){}

    public Message(User user1,User user2,String text){
        this.user2=user2;
        this.user1=user1;
        this.text=text;
    }

    public String gettext() {
        return this.text;
    }

    public User getsender() {
        return this.user1;
    }
    public Date getdate(){
        return this.createdat;
    }
}
