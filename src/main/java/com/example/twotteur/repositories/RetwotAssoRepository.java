package com.example.twotteur.repositories;

import com.example.twotteur.models.RetwotAsso;
import com.example.twotteur.models.Twot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetwotAssoRepository extends JpaRepository<RetwotAsso, Long> {

    Optional<RetwotAsso> findByRetwot(Twot twot);
    Optional<RetwotAsso> findByOriginaltwot(Twot twot);
}

