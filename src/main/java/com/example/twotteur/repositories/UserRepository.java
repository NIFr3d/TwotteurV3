package com.example.twotteur.repositories;

import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Integer countUserByEmail(String email);
    Integer countUserByEmailAndPassword(String email,String password);
    Integer countUserByUsername(String username);
    User getFirstByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByUsernameContaining(String search);
    List<User> findByNicknameContaining(String search);
}
