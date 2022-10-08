package com.example.twotteur.repositories;

import com.example.twotteur.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Integer countUserModelByEmail(String email);
    Integer countUserModelByEmailAndPassword(String email,String password);
    Integer countUserModelByNickname(String nickname);
    UserModel getFirstByEmail(String email);
    UserModel getFirstByNickname(String nickname);
}
