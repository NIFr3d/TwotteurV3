package com.example.twotteur.services;

import com.example.twotteur.models.Message;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public boolean addMessage(User user1, User user2, String text){
        if(!user1.equals(user2)){
            Message m=new Message(user1,user2,text);
            messageRepository.save(m);
            return true;
        }
        return false;
    }
    public List<Message> getConv(User user1,User user2){
        return messageRepository.getByUser1AndUser2(user1,user2);
    }

}
