package com.example.twotteur.repositories;

import com.example.twotteur.models.LikeAsso;
import com.example.twotteur.models.Twot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeAsso, Integer> {
    Integer countByTwot(Twot twot);
}
