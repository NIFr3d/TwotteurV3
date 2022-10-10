package com.example.twotteur.repositories;

import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Integer countUserByEmail(String email);
    Integer countUserByEmailAndPassword(String email,String password);
    Integer countUserByUsername(String username);
    User getFirstByEmail(String email);
    User getFirstByUsername(String username);
}
