package com.example.twotteur.repositories;

import com.example.twotteur.models.Follow;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    List<Follow> getByFollowed(User user);
    List<Follow> getByFollower(User user);
}
