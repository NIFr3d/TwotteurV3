package com.example.twotteur.repositories;

import com.example.twotteur.models.AnswerAsso;
import com.example.twotteur.models.Twot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerAssoRepository extends JpaRepository<AnswerAsso, Long> {

    List<AnswerAsso> findByOriginaltwot(Twot twot);
    List<AnswerAsso> findByAnswer(Twot twot);
    int countByOriginaltwot(Twot twot);
}
