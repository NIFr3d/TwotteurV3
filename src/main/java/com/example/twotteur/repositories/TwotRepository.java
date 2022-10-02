package com.example.twotteur.repositories;

import com.example.twotteur.models.TwotModel;
import com.example.twotteur.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TwotRepository extends JpaRepository<TwotModel, Integer>  {
    List<TwotModel> findTwotModelsByUser(UserModel user);
}
