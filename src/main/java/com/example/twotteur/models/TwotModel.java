package com.example.twotteur.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="twots")
public class TwotModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="text",nullable = false,columnDefinition = "varchar")
    private String text;

    @Column(name="is_answer", nullable=false)
    private boolean is_answer=false;

    @Column(name="original_twot",columnDefinition = "int, default 'null'")
    private Integer original_twot;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id")
    private UserModel user;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false)
    private Date created_at=new Date();

    public TwotModel() {

    }

    public String getText(){
        return this.text;
    }
    public Date getDate(){
        return this.created_at;
    }

    public TwotModel(UserModel user,String text){
        this.user=user;
        this.text=text;
    }

    public int getId(){
        return this.id;
    }
}