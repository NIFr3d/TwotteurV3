package com.example.twotteur.repositories;

import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Integer countUserModelByEmail(String email);
    Integer countUserModelByEmailAndPassword(String email,String password);
    Integer countUserModelByNickname(String nickname);
    User getFirstByEmail(String email);
    Optional<User> findByUsername(String nickname);
}
