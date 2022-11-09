package com.example.twotteur.repositories;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TwotRepository extends JpaRepository<Twot, Long>  {
    List<Twot> findTwotsByUser(User user);
    Optional<Twot> findById(long id);


}
