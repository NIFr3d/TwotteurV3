package com.example.twotteur.repositories;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwotRepository extends JpaRepository<Twot, Integer>  {
    List<Twot> findTwotsByUser(User user);
    List<Twot> findTwotsByOriginaltwot(Twot model);
    Twot findFirstById(int id);

    Integer countByOriginaltwot(Twot twot);
}
