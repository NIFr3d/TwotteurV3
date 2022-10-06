package com.example.twotteur.repositories;

import com.example.twotteur.models.LikeModel;
import com.example.twotteur.models.TwotModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeModel, Integer> {
    int countByTwot(TwotModel twot);
}
