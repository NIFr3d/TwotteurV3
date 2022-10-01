package com.example.twotteur.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="twots")
public class TwotModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="text",nullable = false,columnDefinition = "varchar")
    private String text;

    @Column(name="isAnswer",columnDefinition = "int")
    private boolean isAnswer;

    @Column(name="originalTwot",columnDefinition = "int")
    private int originalTwot;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserModel user;

    @Column(name="created_at", columnDefinition = "DATETIME DEFAULT NOW()")
    private Date created_at;
}