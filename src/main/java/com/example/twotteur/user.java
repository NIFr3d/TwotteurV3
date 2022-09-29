package com.example.twotteur;

import javax.persistence.*;
import org.hibernate.jpa.HibernatePersistenceProvider;

@Entity
@Table(name="users")
public class user{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="nickname")
    private String nickname;

    @Column(name="password")
    private String password;

}