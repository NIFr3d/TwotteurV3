package com.example.twotteur.repositories;

import com.example.twotteur.models.TwotModel;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwotRepository extends JpaRepository<TwotModel, Integer>  {
    List<TwotModel> findTwotModelsByUser(User user);
    List<TwotModel> findTwotModelsByOriginaltwot(TwotModel model);
    TwotModel findFirstById(int id);
    int countByOriginaltwot(TwotModel twot);
}
