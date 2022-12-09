package com.example.twotteur.repositories;

import com.example.twotteur.models.AnswerAsso;
import com.example.twotteur.models.Twot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerAssoRepository extends JpaRepository<AnswerAsso, Long> {

    List<AnswerAsso> findByOriginaltwot(Twot twot);
    Optional<AnswerAsso> findByAnswer(Twot twot);
    int countByOriginaltwot(Twot twot);
    AnswerAsso findFirstByOriginaltwotOrderByCreatedatDesc(Twot original);
    AnswerAsso findFirstByOriginaltwotAndCreatedatBeforeOrderByCreatedatDesc(Twot original, Date date);
}
