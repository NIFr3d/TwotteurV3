package com.example.twotteur;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="com.example.twotteur.twots")
public class twots{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="text")
    private String text;

    @Column(name="isAnswer")
    private boolean isAnswer;

    @Column(name="originalTwot")
    private int originalTwot;

    @Column(name="com.example.twotteur.user")
    private int user;

    @Column(name="created_at", columnDefinition = "DATETIME DEFAULT NOW()")
    private Date created_at;
}