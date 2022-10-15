package com.example.twotteur.repositories;

import com.example.twotteur.models.Message;
import com.example.twotteur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> getByUser1AndUser2(User user1, User user2);
}
