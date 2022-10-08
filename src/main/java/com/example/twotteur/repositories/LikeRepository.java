package com.example.twotteur.repositories;

import com.example.twotteur.models.Like;
import com.example.twotteur.models.Twot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    int countByTwot(Twot twot);
}
