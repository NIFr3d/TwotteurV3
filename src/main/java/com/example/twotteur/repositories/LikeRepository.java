package com.example.twotteur.repositories;

import com.example.twotteur.models.LikeAsso;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<LikeAsso, Long> {
        Integer countByTwot(Twot twot);
        Optional<LikeAsso> findByTwotAndUser(Twot twot, User user);
}
