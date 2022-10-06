package com.example.twotteur.repositories;

import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Integer countUserModelByEmailAndPassword(String email,String password);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User getFirstByEmail(String email);
    Optional<User> findByUsername(String nickname);
}
