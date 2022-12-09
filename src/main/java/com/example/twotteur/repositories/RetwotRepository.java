package com.example.twotteur.repositories;

import com.example.twotteur.models.Retwot;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

import java.util.List;

@Repository
public interface RetwotRepository extends JpaRepository<Retwot, Long>  {
    int countRetwotsByTwot(Twot twot);

    List<Retwot> findRetwotsByUser(User user);

    Optional<Retwot> findByTwotAndUser(Twot twot, User user);

    Retwot findFirstByUserOrderByCreatedatDesc(User user);
    Retwot findFirstByUserAndCreatedatBeforeOrderByCreatedatDesc(User user, Date lastDate);

    List<Retwot> findByTwot(Twot twot);
}

