package com.example.twotteur.repositories;

import com.example.twotteur.models.User;
import com.example.twotteur.models.WSToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WSTokenRepository extends JpaRepository<WSToken, Long> {
    Optional<WSToken> findByUser(User user);
    Optional<WSToken> findByToken(String token);
}
